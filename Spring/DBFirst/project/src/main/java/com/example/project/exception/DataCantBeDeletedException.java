package com.example.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de aplicação. Indica que algum objeto não foi encontrado. Ao ser
 * lançada na camada controller, causa retorno de erro 404 (Not Found), conforme
 * definido pela anotação @ResponseStatus.
 */
@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
public class DataCantBeDeletedException extends RuntimeException {

    private static final long serialVersionUID = 6791812947427807098L;

    public DataCantBeDeletedException(String message) {
        super(message);
    }

    // public DataCantBeDeletedException(String message, Throwable e) {
    //     super(message, e);
    // }
} 