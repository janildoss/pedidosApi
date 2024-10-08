package com.api.pedidosApi.services.exceptions;

import java.io.Serial;

public class ObjectNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L; //diferenciar objetos em execucao

    public ObjectNotFoundException(String msg){
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }

}
