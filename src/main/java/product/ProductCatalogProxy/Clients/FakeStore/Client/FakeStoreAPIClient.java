package product.ProductCatalogProxy.Clients.FakeStore.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import product.ProductCatalogProxy.Clients.FakeStore.Dtos.FakeStoreProductDTO;

@Component
public class FakeStoreAPIClient {

    @Autowired
    public RestTemplateBuilder restTemplateBuilder;


    public FakeStoreProductDTO getProduct(Long id){
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO= restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",FakeStoreProductDTO.class,id).getBody();
        assert fakeStoreProductDTO != null;
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO createProduct(FakeStoreProductDTO fakeStoreProductDTO){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity=restTemplate.postForEntity("https://fakestoreapi.com/products",fakeStoreProductDTO,FakeStoreProductDTO.class);
        return fakeStoreProductDTOResponseEntity.getBody();
        //we are using ResponseEntity<FakeStoreProductDTO> as response type because restTemplate.postForEntity this returns output in that format
    }

}
