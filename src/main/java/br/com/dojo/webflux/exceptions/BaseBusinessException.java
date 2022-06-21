package br.com.dojo.webflux.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseBusinessException extends RuntimeException {

    private final HttpStatus status;

    public BaseBusinessException(final HttpStatus status, final String message) {
        super(message);
        this.status = status;
    }

}
