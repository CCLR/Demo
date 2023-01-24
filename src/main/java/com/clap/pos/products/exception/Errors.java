package com.clap.pos.products.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Errors {
    List<ErrorMessage> errorMessageList;

    Errors() {
        errorMessageList = new ArrayList<>();
    }

    public void addError(ErrorMessage errorMessage) {
        errorMessageList.add(errorMessage);
    }
}
