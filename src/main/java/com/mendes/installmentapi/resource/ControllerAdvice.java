package com.mendes.installmentapi.resource;

import com.mendes.installmentapi.service.ResourceNotFoundException;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Resource Not Found.", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        StringBuilder builder = new StringBuilder();

        result.getFieldErrors().forEach((fieldError) -> {
            builder.append(fieldError.getObjectName()+"."+fieldError.getField()+" : " +fieldError.getDefaultMessage() +" : rejected value [" +fieldError.getRejectedValue() +"]" );
        });
        result.getGlobalErrors().forEach((fieldError) -> {
            builder.append(fieldError.getObjectName()+" : " +fieldError.getDefaultMessage() );
        });

        return new ResponseEntity<Object>(builder.toString() , new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
