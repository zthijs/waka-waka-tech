package nl.zthijs.iprwc.controller;


import nl.zthijs.iprwc.dao.OrderDetailsDAO;
import nl.zthijs.iprwc.dao.UserDAO;
import nl.zthijs.iprwc.entity.OrderDetails;
import nl.zthijs.iprwc.entity.User;
import nl.zthijs.iprwc.exceptions.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderDetailsDAO orderDetailsDAO;
    private final UserDAO userDAO;

    public OrderController(OrderDetailsDAO orderDetailsDAO, UserDAO userDAO) {
        this.orderDetailsDAO = orderDetailsDAO;
        this.userDAO = userDAO;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<OrderDetails> getOrders(@AuthenticationPrincipal String email) {
        User user = userDAO.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        return orderDetailsDAO.getByUser(user);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public OrderDetails getOrder(@AuthenticationPrincipal String email,@PathVariable String id) {
        User user = userDAO.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        return orderDetailsDAO.getByIdAndUser(id, user);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderDetails> getAllOrders() {
        return orderDetailsDAO.getAll();
    }



}
