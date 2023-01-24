package com.clap.pos.products.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ProductHasSupplier")
@IdClass(ProductHasSupplierPK.class)
public class ProductHasSupplier implements Serializable {

    @Id
    private Integer productId;
    @Id
    private Integer supplierId;
    private Double productSupplierPrice;

}
