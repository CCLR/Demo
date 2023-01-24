package com.clap.pos.products.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    private Integer productId;
    private String productName;
    private Double productPrice;
    private Integer productStock;
    private Integer productCategoryId;

}
