package com.clap.pos.products.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {


    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer productId;
    @NotNull
    @NotEmpty
    private String productName;
    @Min(value = 1)
    @NotNull
    private Double productPrice;
    @Min(value = 0)
    @Value("0")
    private Integer productStock;
    @Min(value = 0)
    @NotNull
    private Integer productCategoryId;
}
