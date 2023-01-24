package com.clap.pos.products.repository;


import com.clap.pos.products.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ProductRepository extends CrudRepository<Product, Integer> {

    Optional<Product> findProductByProductId(Integer id);

}
