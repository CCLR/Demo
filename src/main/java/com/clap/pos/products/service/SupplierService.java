package com.clap.pos.products.service;

import com.clap.pos.products.dto.SupplierDto;
import com.clap.pos.products.entities.Supplier;
import com.clap.pos.products.exception.ResourceNotFoundException;
import com.clap.pos.products.mapper.Mapper;
import com.clap.pos.products.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    private final Mapper mapper;

    public List<SupplierDto> readAll() {
        return mapper.convertList((List<Supplier>) supplierRepository.findAll(), SupplierDto.class);
    }

    public void create(SupplierDto supplier) {
        supplierRepository.save(mapper.convert(supplier, Supplier.class));
    }

    public void update(SupplierDto supplier, Integer supplierId) {
        var supplierFromDb = findBySupplierId(supplierId);
        mapper.convert(supplier, supplierFromDb);
        supplierRepository.save(supplierFromDb);
    }

    public void delete(Integer supplierId) {
        supplierRepository.delete(findBySupplierId(supplierId));
    }

    public SupplierDto findBySupplierIdCasted(Integer supplierId) {
        return mapper.convert(findBySupplierId(supplierId), SupplierDto.class);
    }

    public Supplier findBySupplierId(Integer supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + supplierId));

    }

}
