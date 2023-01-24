package com.clap.pos.products.controllers;

import com.clap.pos.products.audit.AuditableRequest;
import com.clap.pos.products.audit.factory.GenericRequestAuditableBuilder;
import com.clap.pos.products.dto.SupplierDto;
import com.clap.pos.products.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/supplier")
public class SupplierController extends BaseController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("")
    @AuditableRequest(name = "getSupplier", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<List<SupplierDto>> getSupplier() {

        return new ResponseEntity<>(supplierService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{supplierId}")
    @AuditableRequest(name = "findSupplierById", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<SupplierDto> findSupplierById(@PathVariable Integer supplierId) {
        return new ResponseEntity<>(supplierService.findBySupplierIdCasted(supplierId), HttpStatus.OK);
    }

    @PostMapping("")
    @AuditableRequest(name = "postSupplier", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> postSupplier(@Valid @RequestBody SupplierDto supplier) {
        supplierService.create(supplier);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{supplierId}")
    @AuditableRequest(name = "updateSupplier", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> updateSupplier(@Valid @RequestBody SupplierDto supplier, @PathVariable Integer supplierId) {
        supplierService.update(supplier, supplierId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{supplierId}")
    @AuditableRequest(name = "deleteSupplier", builder = GenericRequestAuditableBuilder.class)
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer supplierId) {
        supplierService.delete(supplierId);
        return ResponseEntity.ok().build();
    }


}
