package com.api.pedidosApi.ErrorNotFounException;

public class ErrosNotFoundException extends RuntimeException {

    public ErrosNotFoundException(String message) {
        super(message);
    }
}
