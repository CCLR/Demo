package com.clap.pos.products.controllers;

import com.clap.pos.products.audit.AuditableRequest;
import com.clap.pos.products.audit.factory.GenericRequestAuditableBuilder;
import com.clap.pos.products.dto.ProductDto;
import com.clap.pos.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/products")
public class ProductController extends BaseController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    @AuditableRequest(name = "findAll", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<List<ProductDto>> findAll() {
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    @AuditableRequest(name = "findProductById", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<ProductDto> findProductById(@PathVariable Integer productId) {
        return new ResponseEntity<>(productService.findByProductIdCasted(productId), HttpStatus.OK);
    }

    @PostMapping("")
    @AuditableRequest(name = "postProduct", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> postProduct(@Valid @RequestBody ProductDto product) {
        productService.create(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    @AuditableRequest(name = "updateproduct", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductDto product, @PathVariable Integer productId) {
        productService.update(product, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    @AuditableRequest(name = "deleteProduct", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }


}
