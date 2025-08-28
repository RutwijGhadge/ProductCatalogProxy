package product.ProductCatalogProxy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.ProductCatalogProxy.Models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    Product save(Product product);

}
