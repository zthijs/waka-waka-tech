package nl.zthijs.iprwc.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProductCategory {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false, length = 100)
    @Size(min = 1, max = 50)
    @NotBlank
    private String name;

    @Size(min = 1, max = 500)
    @Column(nullable = false, length = 500)
    @NotBlank
    private String description;

    @Size(min = 1, max = 500)
    @Column(nullable = false, length = 500)
    @NotBlank
    private String image;


    @Column(nullable = false, length = 50)
    @Size(min = 1, max = 50)
    @NotBlank
    private String slug;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;


}
