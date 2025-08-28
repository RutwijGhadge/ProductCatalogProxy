package product.ProductCatalogProxy.Services;
import org.springframework.stereotype.Service;
import product.ProductCatalogProxy.Models.Product;
import product.ProductCatalogProxy.Repository.ProductRepo;

import java.util.List;

@Service
public class StorageProductService implements IProductService{

    private final ProductRepo productRepo;

    public StorageProductService(ProductRepo productRepo){
        this.productRepo=productRepo;
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Product resultProduct= productRepo.save(product);
        return  resultProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}
