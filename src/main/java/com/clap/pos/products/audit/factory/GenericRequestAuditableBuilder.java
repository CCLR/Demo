package com.clap.pos.products.audit.factory;


import com.clap.pos.products.controllers.BaseController;
import com.clap.pos.products.entities.Auditory;
import org.aspectj.lang.JoinPoint;
import org.springframework.http.ResponseEntity;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class GenericRequestAuditableBuilder extends AuditableBuilder {

    private ResponseEntity<?> response;
    private Throwable exception;
    private Timestamp startDate;


    @Override
    public Auditory create() {
        return super.create(startDate, response, exception);
    }

    @Override
    public void withThis(JoinPoint jp, Object body) {
        BaseController instance = (BaseController) jp.getThis();
        this.startDate = Timestamp.valueOf(LocalDateTime.now());
        super.buildGenericData(jp, instance.getRequest(), body);
    }

    @Override
    public void withResponse(Object response) {
        this.response = (ResponseEntity<?>) response;
    }

    @Override
    public void withException(Throwable e) {
        this.exception = e;
    }
}
