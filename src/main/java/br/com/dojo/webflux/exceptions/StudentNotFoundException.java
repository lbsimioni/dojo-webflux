package br.com.dojo.webflux.exceptions;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends BaseBusinessException {

    public StudentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Student not found");
    }
}
