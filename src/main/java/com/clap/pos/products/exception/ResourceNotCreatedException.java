package com.clap.pos.products.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotCreatedException extends CustomException {
    public ResourceNotCreatedException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
