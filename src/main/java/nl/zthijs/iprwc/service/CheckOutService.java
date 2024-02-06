package nl.zthijs.iprwc.service;


import nl.zthijs.iprwc.dao.OrderDetailsDAO;
import nl.zthijs.iprwc.dao.OrderItemsDAO;
import nl.zthijs.iprwc.dao.ProductDAO;
import nl.zthijs.iprwc.entity.OrderDetails;
import nl.zthijs.iprwc.entity.OrderItem;
import nl.zthijs.iprwc.entity.Product;
import nl.zthijs.iprwc.entity.User;
import nl.zthijs.iprwc.models.Cart;
import nl.zthijs.iprwc.models.CheckOutRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckOutService {

    private final ProductDAO productDao;
    private final OrderDetailsDAO orderDetailsDAO;
    private final OrderItemsDAO orderItemsDAO;

    public CheckOutService(ProductDAO productDAO, OrderDetailsDAO orderDetailsDAO, OrderItemsDAO orderItemsDAO) {
        this.productDao = productDAO;
        this.orderDetailsDAO = orderDetailsDAO;
        this.orderItemsDAO = orderItemsDAO;
    }


    public String createOrder(CheckOutRequest checkOutData, User user ){



        OrderDetails orderToCreate = new OrderDetails(
                checkOutData.getFirstName(),
                checkOutData.getLastName(),
                user,
                checkOutData.getEmail(),
                checkOutData.getAddress(),
                checkOutData.getCity(),
                checkOutData.getState(),
                calculateTotal(checkOutData.getItems())
        );

        OrderDetails createdOrder = orderDetailsDAO.save(orderToCreate);


        List<OrderItem> items = checkOutData.getItems().stream().map(e -> {
            Product product = productDao.getProductById(e.getId());
            return new OrderItem(
                    product,
                    createdOrder,
                    e.getQuantity(),
                    product.getPrice()
                    );
        }).toList();


        orderItemsDAO.saveAll(items);


        return createdOrder.getId();

    }

    private BigDecimal calculateTotal(List<Cart> items) {
        BigDecimal total = new BigDecimal(0);
        for (Cart item : items) {
            total = total.add(productDao.getProductById(item.getId()).getPrice().multiply(item.getQuantity()));
        }
        return total;
    }

}
