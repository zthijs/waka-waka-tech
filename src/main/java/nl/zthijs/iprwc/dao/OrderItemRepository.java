package nl.zthijs.iprwc.dao;

import nl.zthijs.iprwc.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

}
