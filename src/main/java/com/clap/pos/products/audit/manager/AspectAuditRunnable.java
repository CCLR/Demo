package com.clap.pos.products.audit.manager;


import com.clap.pos.products.audit.AuditableRequest;
import com.clap.pos.products.audit.factory.IAuditableObjectBuilder;
import com.clap.pos.products.repository.AuditRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.lang.reflect.InvocationTargetException;

public class AspectAuditRunnable implements Runnable {

    private IAuditableObjectBuilder current;
    private AuditRepository repo;
    private static final String LOG_STEP_KEY = "fdps.step";
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    public AspectAuditRunnable(ProceedingJoinPoint joinPoint, AuditRepository repo, Object body) {
        this.repo = repo;
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        AuditableRequest annotation = methodSignature.getMethod().getAnnotation(AuditableRequest.class);
        if(annotation!= null) {
            Class<? extends IAuditableObjectBuilder> factoryClass = annotation.builder();
            try {
                this.current = factoryClass.getConstructor().newInstance();
                this.current.withThis(joinPoint, body);
                addToMdc(annotation);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }            
        }

    }

    private void addToMdc(AuditableRequest annotation) {
        String name = annotation.name();
        if(name!=null && !"".equals(name.trim())) {
            MDC.put(LOG_STEP_KEY, name);
        }
    }

    @Override
    public void run() {
        try {
            repo.save(this.current.create());
        } catch (Exception e) {
            logger.error("error al auditar", e);
        }
    }

    public void setResponse(Object response) {
        this.current.withResponse(response);
    }

    public void setException(Throwable e) {
        this.current.withException(e);
    }
}
