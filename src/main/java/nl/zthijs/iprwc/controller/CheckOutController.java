package nl.zthijs.iprwc.controller;

import jakarta.validation.Valid;
import nl.zthijs.iprwc.dao.OrderDetailsDAO;
import nl.zthijs.iprwc.dao.UserDAO;
import nl.zthijs.iprwc.entity.OrderDetails;
import nl.zthijs.iprwc.entity.User;
import nl.zthijs.iprwc.exceptions.ResourceNotFoundException;
import nl.zthijs.iprwc.models.Cart;
import nl.zthijs.iprwc.models.CheckOutRequest;
import nl.zthijs.iprwc.service.CheckOutService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/checkout")
public class CheckOutController {

    private final CheckOutService checkOutService;
    private final UserDAO userDAO;

    private final OrderDetailsDAO orderDetailsDAO;

    public CheckOutController(CheckOutService checkOutService, UserDAO userDAO, OrderDetailsDAO orderDetailsDAO) {
        this.userDAO = userDAO;
        this.checkOutService = checkOutService;
        this.orderDetailsDAO = orderDetailsDAO;

    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrderDetails> checkOut(@AuthenticationPrincipal String email, @RequestBody @Valid CheckOutRequest checkOutData){

        User user = userDAO.findByEmail(email).orElseThrow(ResourceNotFoundException::new);

        String createdOrderId = checkOutService.createOrder(checkOutData, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailsDAO.getById(createdOrderId));

    }

}
