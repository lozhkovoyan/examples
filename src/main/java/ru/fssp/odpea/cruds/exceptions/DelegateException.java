package ru.fssp.odpea.cruds.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DelegateException extends RuntimeException {
    public DelegateException(String message) {
        super(message);
    }

}
