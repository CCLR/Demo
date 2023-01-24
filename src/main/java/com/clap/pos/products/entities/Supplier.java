package com.clap.pos.products.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Supplier")
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    private Integer supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierPhone1;
    private String supplierPhone2;

}
