package product.ProductCatalogProxy.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import product.ProductCatalogProxy.Models.Product;
import java.util.List;

@Getter
@Setter
@ToString
public class CategoryDTO {
    private String name;
    private String description;
    private List<Product> products;
}
