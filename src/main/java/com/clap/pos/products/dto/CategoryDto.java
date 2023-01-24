package com.clap.pos.products.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer categoryId;
    @Size(min = 4, max = 255)
    @NotNull
    private String categoryName;
    @Size(min = 10, max = 255)
    private String categoryDescription;
}
