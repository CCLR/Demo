package com.clap.pos.products.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductHasSupplierPK implements Serializable {

    @Column
    private Integer productId;
    @Column
    private Integer supplierId;


}
