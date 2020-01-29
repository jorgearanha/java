package com.example.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * BusinessRuleException
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BusinessRuleException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BusinessRuleException(String message) {
        super(message);
    }
    
}