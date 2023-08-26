package springrestapii.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailException extends RuntimeException {

    private String message;

    public EmailException(String message) {
        super(message);
    }

}
