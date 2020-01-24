package com.example.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de aplicação. Indica que algum objeto não foi encontrado. Ao ser
 * lançada na camada controller, causa retorno de erro 404 (Not Found), conforme
 * definido pela anotação @ResponseStatus.
 */
@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
public class EventoCantBeCanceledException extends RuntimeException {

    private static final long serialVersionUID = -7827943712130231999L;

    public EventoCantBeCanceledException(String message) {
        super(message);
    }

    // public EventoCantBeCreatedException(String message, Throwable e) {
    //     super(message, e);
    // }
} 