package product.ProductCatalogProxy.Services;

import product.ProductCatalogProxy.Clients.FakeStore.Dtos.FakeStoreProductDTO;
import product.ProductCatalogProxy.DTOs.ProductDTO;
import product.ProductCatalogProxy.Models.Product;

import java.util.List;

/*
Controller and Service layer classes are Concrete in nature
They should not be tightly coupled with each other.
That is why this interface is needed to decouple the tight coupling with Service and Controller layer classes.
 */

public interface IProductService {

    List<Product> getProducts();
    Product getProduct(Long id);
    Product createProduct(Product product );
    Product updateProduct(Long id, Product product);
}


//Imp : Why we will be using Arrays and Not a List as Return Type for getProducts() methods
/*
-> Generics will not be able to determine the Return type of Data they contain ex. List<Products> ot List<Integers>
   to avoid this  : In this case List<Products> and List<Integers> will be treated as similar return types
   so restTemplate.getForEntity() this method will be error out
   we will simply use Arrays

   and this is also the reason why Jackson allows only arrays and not any other type
   */
