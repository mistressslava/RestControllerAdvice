package de.neuefische.springexceptionhandlingtask;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerNoSuchElementException(NoSuchElementException e, HttpServletRequest request) {
        ErrorMessage body = new ErrorMessage(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return "Error: " + body.message() +
                "\n[Reason: " + body.error() +
                "\nStatuscode: " + body.status() +
                "\nTime: " + body.timestamp() +
                "\nPath: " + body.path() +
                "]";
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handlerNullPointerException(NullPointerException e, HttpServletRequest request) {
        ErrorMessage body = new ErrorMessage(
                Instant.now(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return "Error: " + body.message() +
                "\n[Reason: " + body.error() +
                "\nStatuscode: " + body.status() +
                "\nTime: " + body.timestamp() +
                "\nPath: " + body.path() +
                "]";
    }
}
