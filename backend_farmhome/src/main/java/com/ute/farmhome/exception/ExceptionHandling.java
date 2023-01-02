package com.ute.farmhome.exception;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerNotFound(Exception e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = ResourceNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity ResourceNotFound(ResourceNotFound ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(ex.toString());
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Forbidden");
        response.put("error message", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidation(ValidationException e) {
        Map<String, String> map = new HashMap<>();
        if(e.getMessage() != null) {
            map.put("error message", e.getMessage());
            map.put("status code", "400");
            return ResponseEntity.badRequest().body(map);
        }
        map = e.getErrors();
        map.put("status code", "400");
        return ResponseEntity.badRequest().body(map);
    }
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleAuthenticationFail(AuthenticationException e) {
        Map<String, String> map = new HashMap<>();
        map.put("status code", "401");
        map.put("message", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleFileSizeExceed(FileSizeLimitExceededException e) {
        Map<String, String> map = new HashMap<>();
        map.put("status code", "400");
        map.put ("message", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExceedAmount.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleExceedAmount(ExceedAmount e) {
        return ResponseEntity.badRequest().body(e.toString());
    }
}
