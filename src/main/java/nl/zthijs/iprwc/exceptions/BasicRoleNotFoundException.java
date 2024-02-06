package nl.zthijs.iprwc.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BasicRoleNotFoundException extends RuntimeException {
}
