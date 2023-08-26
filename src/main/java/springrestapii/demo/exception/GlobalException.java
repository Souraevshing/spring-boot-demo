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

    // this method will handle resource not found or 404 exception for any request
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

    // this method will handle email already exists or 400 exception for any request
    @ExceptionHandler(EmailException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(EmailException emailException, WebRequest request) {

        Error error = new Error(
                LocalDateTime.now(),
                emailException.getMessage(),
                request.getDescription(false),
                400L
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    // if spring throws any other exceptions other than above exceptions, then this method will handle the exception
    // and will throw Internal server error or500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleResourceNotFoundException(Exception exception, WebRequest request) {

        Error error = new Error(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                500L
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
