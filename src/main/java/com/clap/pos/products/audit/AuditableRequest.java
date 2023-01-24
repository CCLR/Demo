package com.clap.pos.products.audit;


import com.clap.pos.products.audit.factory.IAuditableObjectBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditableRequest {

    String name() default "";

    Class<? extends IAuditableObjectBuilder> builder();
}
