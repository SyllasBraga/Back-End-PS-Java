package br.com.banco.controllers.exceptions;

import br.com.banco.exceptions.DataIncorretaException;
import br.com.banco.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFounException(NotFoundException e, HttpServletRequest http){
        StandardError error = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(),
                "Not Found", e.getMessage(), http.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIncorretaException.class)
    public ResponseEntity<StandardError> dataIncorretaException(DataIncorretaException e, HttpServletRequest http){
        StandardError error = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                "Bad Request", e.getMessage(), http.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
