package nl.zthijs.iprwc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderItem {

    @Id
    @UuidGenerator
    private String id;

    @DecimalMin(value = "0")
    @DecimalMax(value = "5")
    @Digits(integer = 2, fraction = 0)
    private BigDecimal quantity;

    private BigDecimal purchasePrice;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JsonIgnore
    private OrderDetails orderDetails;



    public OrderItem(Product product, OrderDetails orderDetails, BigDecimal quantity, BigDecimal purchasePrice) {
        this.product = product;
        this.orderDetails = orderDetails;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

}
