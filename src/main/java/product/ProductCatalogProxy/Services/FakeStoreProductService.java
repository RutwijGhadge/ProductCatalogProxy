package product.ProductCatalogProxy.Services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.ProductCatalogProxy.Clients.FakeStore.Client.FakeStoreAPIClient;
import product.ProductCatalogProxy.Clients.FakeStore.Dtos.FakeStoreProductDTO;
import product.ProductCatalogProxy.Models.Category;
import product.ProductCatalogProxy.Models.Product;
import product.ProductCatalogProxy.Repository.ProductRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FakeStoreProductService implements IProductService {

    private final RestTemplateBuilder restTemplateBuilder;
    private final FakeStoreAPIClient fakeStoreAPIClient;


    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder,FakeStoreAPIClient fakeStoreAPIClient){
        this.restTemplateBuilder=restTemplateBuilder;
        this.fakeStoreAPIClient=fakeStoreAPIClient;
    }

    @Override
    public List<Product> getProducts() {
        //this will return List of Products
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreProductDTO[] fakeStoreProductDTOS=restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class).getBody();
        List<Product>products=new ArrayList<>();
        assert fakeStoreProductDTOS != null;
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOS){
            products.add(convertToProduct(fakeStoreProductDTO));
        }
        return products;
    }

    @Override
    public Product getProduct(Long productId){
        return convertToProduct(fakeStoreAPIClient.getProduct(productId));
    }

    @Override
    public Product createProduct(Product product){
        /*
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO>fakeStoreProductDTOResponseEntity=restTemplate.postForEntity("https://fakestoreapi.com/products",product,FakeStoreProductDTO.class);
        FakeStoreProductDTO fakeStoreProductDTO=fakeStoreProductDTOResponseEntity.getBody();
        assert fakeStoreProductDTO != null;
        return convertToProduct(fakeStoreProductDTO);
        */
        //we are using ResponseEntity<FakeStoreProductDTO> as response type because restTemplate.postForEntity this returns output in that format

        FakeStoreProductDTO fakeStoreProductDTO=getFakeStoreProductDTOFromProduct(product);
        FakeStoreProductDTO responseFakeStoreProductDTO=fakeStoreAPIClient.createProduct(fakeStoreProductDTO);
        return convertToProduct(responseFakeStoreProductDTO);

    }

    @Override
    public Product updateProduct(Long id , Product product){
        RestTemplate restTemplate= restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO=restTemplate.patchForObject("https://fakestoreapi.com/products/{id}",product,FakeStoreProductDTO.class,id);
        assert fakeStoreProductDTO != null;
        return convertToProduct(fakeStoreProductDTO);
    }


    private static Product convertToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product=new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setCreatedAt(new Date());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageURL(fakeStoreProductDTO.getImage());
        product.setTitle(fakeStoreProductDTO.getTitle());

        Category category=new Category();
        category.setId(fakeStoreProductDTO.getId());
        category.setCreatedAt(new Date());
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
        //conversion of class ProductDTO to Product
    }


    private FakeStoreProductDTO getFakeStoreProductDTOFromProduct(Product product){
        FakeStoreProductDTO fakeStoreProductDTO=new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        fakeStoreProductDTO.setImage(product.getImageURL());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setTitle(product.getTitle());
        return fakeStoreProductDTO;
    }

}

/*
Service Layer :
Takes Input as the FakeStoreProductDTO and Output to controller in form of Product Class
For Controller Input from user - ProductDTO and Output to user-> Product

3rd party API should not be directly called from service Layer
It should be called from client
 */

