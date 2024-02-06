package nl.zthijs.iprwc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.zthijs.iprwc.dao.CategoryDAO;
import nl.zthijs.iprwc.entity.ProductCategory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryDAO categoryDao;

    @GetMapping
    public List<ProductCategory> getCategories() {
        return categoryDao.getAll();
    }

    @GetMapping("/{id}")
    public ProductCategory getCategoryById(@PathVariable String id) {
        return categoryDao.getCategoryById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductCategory createCategory( @Valid @RequestBody ProductCategory category) {
        return categoryDao.saveCategory(category);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductCategory updateCategory(@PathVariable String id, @Valid @RequestBody ProductCategory category) {

        if (!id.equals(category.getId())) {
            throw new IllegalArgumentException("Id's do not match");
        }

        return categoryDao.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable String id) {
        categoryDao.deleteCategory(id);
    }

}
