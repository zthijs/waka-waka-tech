package nl.zthijs.iprwc.dao;

import nl.zthijs.iprwc.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    public List<Product> findByAvailableTrue();
}
