package springrestapii.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ExceptionHandler is used to throw custom exception like here we are throwing custom error message, code.
 * In parameter of @ExceptionHandler we pass Exception class name which we want to throw, here we are throwing exception from
 * ResourceNotFound class */

/**
 * @ControllerAdvice is used to throw global level exception and it will throw exception to controller request url whenever any
 * kind of exception occurs.
 */


@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

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


    // this method will handle validation when the request fields are not valid and throw errors.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode statusCode,
                                                                  WebRequest request) {

        // errors is a map to store all the error details including field name and message.
        Map<String, String> errors = new HashMap<>();
        // fetch all global and field errors and add to errorList
        List<ObjectError> errorList = exception.getAllErrors();

        // traverse from list of errors and extract field names and message from error object.
        errorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
