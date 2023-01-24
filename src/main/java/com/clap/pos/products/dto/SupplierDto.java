package com.clap.pos.products.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class SupplierDto implements Serializable {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer supplierId;
    @Size(min = 4, max = 255)
    private String supplierName;
    @Size(min = 4, max = 255)
    private String supplierAddress;
    @Size(min = 8, max = 14)
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$*", message = "Phone number format not valid")
    private String supplierPhone1;
    @Size(min = 8, max = 14)
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Phone number format not valid")
    private String supplierPhone2;

}
