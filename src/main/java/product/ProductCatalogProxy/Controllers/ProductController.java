package product.ProductCatalogProxy.Controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import product.ProductCatalogProxy.DTOs.ProductDTO;
import product.ProductCatalogProxy.Models.Category;
import product.ProductCatalogProxy.Models.Product;
import product.ProductCatalogProxy.Services.IProductService;

import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    IProductService fakeProductService;
    public ProductController(IProductService productService){
        this.fakeProductService=productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        List<Product>products=fakeProductService.getProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        //Adding custom Response Headers
        MultiValueMap<String,String>headers=new LinkedMultiValueMap<>();
        headers.add("customHeader","output header");
        try {
            //Throwing exception : validation of Path variable
            if (id < 1) {
                throw new IllegalArgumentException("product id is not correct");
            }
            Product product = fakeProductService.getProduct(id);
            return new ResponseEntity<>(product,headers, HttpStatus.OK);
        }catch (Exception exception){
            throw exception;
            //catching the exception and returning the HTTP status code of our choice
          //  return new ResponseEntity<>(headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody ProductDTO  productDTO){
        Product product=getProduct(productDTO);
        return fakeProductService.createProduct(product);
    }

    @PatchMapping("/updateProducts/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO){
        Product product = getProduct(productDTO);
        Product product1=fakeProductService.updateProduct(id,product);
        return new ResponseEntity<>(product1,HttpStatus.OK);
    }

    private Product getProduct(ProductDTO productDTO){
        Product product=new Product();
        product.setCreatedAt(new Date());
        product.setId(productDTO.getId());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImage());
        product.setTitle(productDTO.getTitle());

        Category category=new Category();
        category.setId(productDTO.getId());
        category.setCreatedAt(new Date());
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        return product;
        //conversion of class ProductDTO to Product
    }

/*
  @ExceptionHandler({IllegalArgumentException.class,NullPointerException.class})
    private ResponseEntity<String> handleException(){
        return new ResponseEntity<>("Id Value id Negative",HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

}
/*
Controller Layer : User will send the Input in the format of ProductDTO
we will convert the ProductDTO to  in the Controller Layer itself.
and send the Product object to Service Layer.

FakeStoreDTO : FakeStore API understands the Data in FakeStoreDTO Format only.
 */


//Jackson Library is used to convert Object into Json and Vice Versa.
//ResponseEntity class is Responsible for Sending the HTTP Response status : 200 Ok , 4xx , Internal server error -500