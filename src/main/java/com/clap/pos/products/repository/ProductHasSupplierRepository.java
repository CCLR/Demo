package com.clap.pos.products.repository;


import com.clap.pos.products.entities.ProductHasSupplier;
import com.clap.pos.products.entities.ProductHasSupplierPK;
import org.springframework.data.repository.CrudRepository;


public interface ProductHasSupplierRepository extends CrudRepository<ProductHasSupplier, ProductHasSupplierPK> {

}
