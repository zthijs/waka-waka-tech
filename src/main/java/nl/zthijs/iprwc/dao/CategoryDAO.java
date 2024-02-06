package nl.zthijs.iprwc.dao;

import lombok.AllArgsConstructor;
import nl.zthijs.iprwc.entity.ProductCategory;
import nl.zthijs.iprwc.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CategoryDAO {

    private final CategoryRepository categoryRepository;

    public List<ProductCategory> getAll(){
        return this.categoryRepository.findAll();
    }

    public ProductCategory getCategoryById(String id){
        return this.categoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public ProductCategory saveCategory(ProductCategory category){
        return this.categoryRepository.save(category);
    }

    public void deleteCategory(String id){
        this.categoryRepository.deleteById(id);
    }

    public long count(){
        return this.categoryRepository.count();
    }
}
