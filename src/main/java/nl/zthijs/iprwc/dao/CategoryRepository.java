package nl.zthijs.iprwc.dao;

import nl.zthijs.iprwc.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ProductCategory, String> {
}
