package com.clap.pos.products.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CustomException {

    public ResourceNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND.value());
    }
}
