package springrestapii.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * @ExceptionHandler is used to throw custom exception like here we are throwing custom error message, code.
 * In parameter of @ExceptionHandler we pass Exception class name which we want to throw, here we are throwing exception from
 * ResourceNotFound class */

/**
 * @ControllerAdvice is used to throw global level exception and it will throw exception to controller request url whenever any
 * kind of exception occurs.
 */


@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFound resourceNotFound, WebRequest request) {

        Error error = new Error(
                LocalDateTime.now(),
                resourceNotFound.getMessage(),
                request.getDescription(false),
                404L
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

}
