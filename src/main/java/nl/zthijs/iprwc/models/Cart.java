package nl.zthijs.iprwc.models;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {

    @NotBlank
    @Size(min = 1, max = 100)
    private String id;


    @DecimalMin(value = "0")
    @DecimalMax(value = "5")
    @Digits(integer = 5, fraction = 0)
    private BigDecimal quantity;

}
