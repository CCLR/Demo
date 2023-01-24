package com.clap.pos.products.controllers;

import com.clap.pos.products.audit.AuditableRequest;
import com.clap.pos.products.audit.factory.GenericRequestAuditableBuilder;
import com.clap.pos.products.dto.ProductHasSupplierDto;
import com.clap.pos.products.entities.ProductHasSupplierPK;
import com.clap.pos.products.service.ProductHasSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/product-has-supplier")
public class ProductHasSupplierController extends BaseController {

    @Autowired
    private ProductHasSupplierService productHasSupplierService;

    @GetMapping("")
    @AuditableRequest(name = "getProductHasSupplier", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<List<ProductHasSupplierDto>> getProductHasSupplier() {
        return new ResponseEntity<>(productHasSupplierService.readAll(), HttpStatus.OK);
    }

    @PostMapping("/find-by-id")
    @AuditableRequest(name = "findProductHasSupplierById", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<ProductHasSupplierDto> findProductHasSupplierById(@Valid @RequestBody ProductHasSupplierPK productHasSupplierPK) {
        return new ResponseEntity<>(productHasSupplierService.findByProductHasSupplierIdCasted(productHasSupplierPK), HttpStatus.OK);
    }

    @PostMapping("")
    @AuditableRequest(name = "postProductHasSupplier", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> postProductHasSupplier(@Valid @RequestBody ProductHasSupplierDto productHasSupplier) {
        productHasSupplierService.create(productHasSupplier);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productHasSupplierId}")
    @AuditableRequest(name = "updateProductHasSupplier", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> updateProductHasSupplier(@Valid @RequestBody ProductHasSupplierDto productHasSupplier) {
        productHasSupplierService.update(productHasSupplier);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("")
    @AuditableRequest(name = "deleteProductHasSupplier", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> deleteProductHasSupplier(@RequestBody ProductHasSupplierPK productHasSupplierId) {
        productHasSupplierService.delete(productHasSupplierId);
        return ResponseEntity.ok().build();
    }


}
