package nl.zthijs.iprwc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnmetPasswordRequirementsException extends RuntimeException {
    public UnmetPasswordRequirementsException() {
        super("Password does not meet requirements");
    }
}
