package nl.zthijs.iprwc.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CheckOutRequest {


    @Size(min = 1, max = 100)
    @NotBlank
    private String firstName;

    @Size(min = 1, max = 100)
    @NotBlank
    private String lastName;

    @Email
    @Size(min = 1, max = 100)
    @NotBlank
    private String email;

    @Size(min = 1, max = 150)
    @NotBlank
    private String address;

    @Size(min = 1, max = 100)
    @NotBlank
    private String city;

    @Size(min = 1, max = 100)
    @NotBlank
    private String state;

    @Valid
    private List<Cart> items;


}
