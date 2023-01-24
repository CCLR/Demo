package com.clap.pos.products.audit.factory;


import com.clap.pos.products.audit.AuditableRequest;
import com.clap.pos.products.entities.Auditory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
public abstract class AuditableBuilder implements IAuditableObjectBuilder {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private String urlHTTPMethod;
    private String requestUri;
    private String body;
    private String step;

    public void buildGenericData(JoinPoint jp, HttpServletRequest request, Object body) {
        this.urlHTTPMethod = request.getMethod();
        var sb = new StringBuilder(request.getRequestURL().toString());
        final var queryString = request.getQueryString();
        if (queryString != null) {
            sb.append("?").append(queryString);
        }
        this.requestUri = sb.toString();
        try {
            this.body =  objectMapper.writeValueAsString(body);
        } catch (IOException e) {
            this.body = "error recuperando mensaje";
        }
        var methodSignature = (MethodSignature) jp.getSignature();
        AuditableRequest annotation = methodSignature.getMethod().getAnnotation(AuditableRequest.class);
        this.step = annotation.name();
    }

    public Auditory create(Timestamp startDate, ResponseEntity<?> response, Throwable exception) {
        var endDate = LocalDateTime.now();
        var auditableObject = new Auditory();
        auditableObject.setStartDate(startDate);
        auditableObject.setDuration(Duration.between(startDate.toLocalDateTime(), endDate).toMillis());
        auditableObject.setHttpMethod(this.urlHTTPMethod);
        auditableObject.setRequestUri(this.requestUri);
        auditableObject.setBody(this.body);
        if (response != null) {
            auditableObject.setResponse(objectMapper.valueToTree(response).toString());
            auditableObject.setHttpStatus(response.getStatusCode().toString());
        }
        if (exception != null) {
            if (exception instanceof RestClientResponseException) {
                var restClientResponseException = (RestClientResponseException) exception;
                auditableObject.setHttpStatus(restClientResponseException.getRawStatusCode() + "");
                auditableObject.setResponse(objectMapper.valueToTree(restClientResponseException.getResponseBodyAsString()).toString());

            } else {
                auditableObject.setHttpStatus("500");
                String stackTrace = ExceptionUtils.getStackTrace(exception);
                auditableObject.setException(exception.getMessage() + " " + stackTrace.substring(0, Math.min(stackTrace.length(), 3000)));
            }

        }
        return auditableObject;
    }

}
