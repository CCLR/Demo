package com.clap.pos.products.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Auditory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date startDate;
    private Long duration;
    private String httpMethod;
    private String requestUri;
    @Column(columnDefinition = "TEXT")
    private String body;
    @Column(columnDefinition = "TEXT")
    private String response;
    private String httpStatus;
    @Column(columnDefinition = "TEXT")
    private String exception;


}
