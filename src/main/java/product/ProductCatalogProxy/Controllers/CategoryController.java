package product.ProductCatalogProxy.Controllers;

import org.springframework.web.bind.annotation.*;
import product.ProductCatalogProxy.DTOs.CategoryDTO;

@RestController
public class CategoryController {

    @GetMapping("/category")
    public String getCategory(){
        return "Returning the Category of a product";
    }

    @GetMapping("/category/{id}")
    public String getCategoryOfProduct(@PathVariable int id){
        return "returning Category of Specific Product with id:"+id;
    }

    @PostMapping("/createCategory")
    public String createCategory(@RequestBody CategoryDTO categoryDTO){
        return "Created Category "+categoryDTO;
    }
}
