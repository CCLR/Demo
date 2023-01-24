package com.clap.pos.products.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductHasSupplierDto {

    @NotNull
    @Size(min = 1, max = 5)
    private Integer productId;
    @NotNull
    @Size(min = 1, max = 5)
    private Integer supplierId;
    @NotNull
    @Size(min = 1, max = 10)
    private Double productSupplierPrice;

}
