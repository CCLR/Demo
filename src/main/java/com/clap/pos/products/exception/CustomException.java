package com.clap.pos.products.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class CustomException extends RuntimeException {

    private String message;
    private int statusCode;
}
