package nl.zthijs.iprwc.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginCredentials {

    @Email
    public String email;

    @NotEmpty
    public String password;

}
