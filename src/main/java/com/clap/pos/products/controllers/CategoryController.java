package com.clap.pos.products.controllers;

import com.clap.pos.products.audit.AuditableRequest;
import com.clap.pos.products.audit.factory.GenericRequestAuditableBuilder;
import com.clap.pos.products.dto.CategoryDto;
import com.clap.pos.products.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    @AuditableRequest(name = "getCategory", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<List<CategoryDto>> getCategory() {
        return new ResponseEntity<>(categoryService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    @AuditableRequest(name = "findCategoryById", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Integer categoryId) {
        return new ResponseEntity<>(categoryService.findCategoryByIdCasted(categoryId), HttpStatus.OK);
    }

    @PostMapping("")
    @AuditableRequest(name = "createCategory", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> postCategory(@Valid @RequestBody CategoryDto category) {
        categoryService.create(category);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{categoryId}")
    @AuditableRequest(name = "updateCategory", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody CategoryDto category, @PathVariable Integer categoryId) {
        categoryService.update(category, categoryId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{categoryId}")
    @AuditableRequest(name = "deleteCategory", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok().build();
    }


}
