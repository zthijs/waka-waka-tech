package nl.zthijs.iprwc.dao;

import lombok.AllArgsConstructor;
import nl.zthijs.iprwc.entity.Product;
import nl.zthijs.iprwc.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProductDAO {

    private final ProductRepository productRepository;

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public List<Product> getAllActive(){
        return this.productRepository.findByAvailableTrue();
    }

    public Product getProductById(String id){
        return this.productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Product saveProduct(Product product){
        return this.productRepository.save(product);
    }

    public long count(){
        return this.productRepository.count();
    }
}
