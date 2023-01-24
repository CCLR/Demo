package com.clap.pos.products.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@Data
public abstract class BaseController {

    @Autowired
    private HttpServletRequest request;
}
