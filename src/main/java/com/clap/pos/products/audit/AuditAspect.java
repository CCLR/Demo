package com.clap.pos.products.audit;

import com.clap.pos.products.audit.manager.AspectAuditRunnable;
import com.clap.pos.products.audit.manager.AuditManager;
import com.clap.pos.products.repository.AuditRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditAspect {


    @Autowired
    private AuditRepository auditRepository;

    @Pointcut("@annotation(AuditableRequest)")
    public void isAuditable() {
        // Do nothing because of X and Y.
    }

    @Around("isAuditable() && args(..,@RequestBody body) && !((@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)) ) ")
    public Object logAdapter(ProceedingJoinPoint joinPoint , Object body) throws Throwable {
        var auditRunnable = new AspectAuditRunnable(joinPoint, auditRepository, body );
        try {
            Object response = joinPoint.proceed();
            auditRunnable.setResponse(response);
            return response;

        } catch (Throwable e) {
            auditRunnable.setException(e);
            throw e;
        } finally {
            AuditManager.audit(auditRunnable);
        }
    }

    @Around("isAuditable() && (@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping))")
    public Object logAdapter(ProceedingJoinPoint joinPoint) throws Throwable {
        var auditRunnable = new AspectAuditRunnable(joinPoint, auditRepository,                 null );
        try {
            Object response = joinPoint.proceed();
            auditRunnable.setResponse(response);
            return response;

        } catch (Throwable e) {
            auditRunnable.setException(e);
            throw e;
        } finally {
            AuditManager.audit(auditRunnable);
        }
    }

}
