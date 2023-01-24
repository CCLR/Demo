package com.clap.pos.products.audit.factory;

import com.clap.pos.products.entities.Auditory;
import org.aspectj.lang.JoinPoint;

public interface IAuditableObjectBuilder {

    public Auditory create();

    public void withThis(JoinPoint instance, Object body);

    public void withResponse(Object response);

    public void withException(Throwable e);
}
