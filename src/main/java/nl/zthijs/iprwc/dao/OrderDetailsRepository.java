package nl.zthijs.iprwc.dao;

import nl.zthijs.iprwc.entity.OrderDetails;
import nl.zthijs.iprwc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {
    public List<OrderDetails> getOrderDetailsByUser(User user);

    Optional<OrderDetails> getOrderDetailsByIdAndUser(String id, User user);
}
