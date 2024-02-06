package nl.zthijs.iprwc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @UuidGenerator
    private String id;

    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    @NotBlank
    private String name;

    @Size(min = 1, max = 500)
    @Column(nullable = false, length = 500)
    @NotBlank
    private String description;


    @Column(nullable = false)
    private boolean available = true;


    @Column(nullable = false)
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "999999.99")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;


    @Size(min = 1, max = 500)
    @Column(nullable = false, length = 500)
    @URL
    private String imageUrl;

    @ManyToOne
    @NotNull
    @Valid
    private ProductCategory category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderItem> orderItems;

    public Product(String name, String description, BigDecimal price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

}
