package nl.zthijs.iprwc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.zthijs.iprwc.dao.ProductDAO;
import nl.zthijs.iprwc.entity.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductDAO productDao;

    @GetMapping
    public List<Product> getActiveProducts() {
        return productDao.getAllActive();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productDao.getProductById(id);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Product createProduct(@RequestBody @Valid Product product) {
        return productDao.saveProduct(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product updateProduct(@PathVariable String id, @RequestBody @Valid Product product) {

        if (!id.equals(product.getId())) {
            throw new IllegalArgumentException("Id's do not match");
        }

        return productDao.saveProduct(product);
    }
}
