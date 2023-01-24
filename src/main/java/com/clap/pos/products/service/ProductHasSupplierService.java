package com.clap.pos.products.service;

import com.clap.pos.products.dto.ProductHasSupplierDto;
import com.clap.pos.products.entities.ProductHasSupplier;
import com.clap.pos.products.entities.ProductHasSupplierPK;
import com.clap.pos.products.exception.ResourceNotCreatedException;
import com.clap.pos.products.exception.ResourceNotFoundException;
import com.clap.pos.products.mapper.Mapper;
import com.clap.pos.products.repository.ProductHasSupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ProductHasSupplierService {

    private final ProductHasSupplierRepository productHasSupplierRepository;

    private final ProductService productService;

    private final SupplierService supplierService;


    private final Mapper mapper;

    public List<ProductHasSupplierDto> readAll() {
        return mapper.convertList((List<ProductHasSupplier>) productHasSupplierRepository.findAll(), ProductHasSupplierDto.class);
    }

    public void create(ProductHasSupplierDto productHasSupplier) {
        productService.findByProductId(productHasSupplier.getProductId());
        supplierService.findBySupplierId(productHasSupplier.getSupplierId());
        productHasSupplierRepository.findById(new ProductHasSupplierPK(productHasSupplier.getProductId(), productHasSupplier.getSupplierId())).ifPresent(productHasSupplier1 -> {
            var errorDetail = String.format("Duplicated Product has supplier with id = ProductHasSupplierPK(productId=%d, supplierId=%d)", productHasSupplier1.getProductId(), productHasSupplier1.getSupplierId());
            throw new ResourceNotCreatedException(errorDetail);
        });
        productHasSupplierRepository.save(mapper.convert(productHasSupplier, ProductHasSupplier.class));
    }

    public void update(ProductHasSupplierDto productHasSupplier) {
        var productHasSupplierFromDb = findByProductHasSupplierId(new ProductHasSupplierPK(productHasSupplier.getProductId(), productHasSupplier.getSupplierId()));
        mapper.convert(productHasSupplier, productHasSupplierFromDb);
        productHasSupplierRepository.save(productHasSupplierFromDb);
    }

    public void delete(ProductHasSupplierPK productHasSupplierId) {
        productHasSupplierRepository.delete(findByProductHasSupplierId(productHasSupplierId));
    }


    public ProductHasSupplierDto findByProductHasSupplierIdCasted(ProductHasSupplierPK productHasSupplierPK) {
        return mapper.convert(findByProductHasSupplierId(productHasSupplierPK), ProductHasSupplierDto.class);
    }


    private ProductHasSupplier findByProductHasSupplierId(ProductHasSupplierPK productHasSupplierId) {
        return productHasSupplierRepository.findById(productHasSupplierId).orElseThrow(() -> new ResourceNotFoundException("Not found Product has supplier with id" + productHasSupplierId));
    }


}
