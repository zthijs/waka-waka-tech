package nl.zthijs.iprwc.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderDetails {

    @Id
    @UuidGenerator
    private String id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private String firstName;

    public OrderDetails(String firstName, String lastName, User user, String email, String address, String city, String state, BigDecimal totalPrice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.totalPrice = totalPrice;
    }

    private String lastName;

    @ManyToOne
    @JsonIgnore
    private User user;

    private String email;
    private String address;
    private String city;
    private String state;


    @Positive
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "orderDetails")
    private List<OrderItem> items;


}
