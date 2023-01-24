package com.clap.pos.products.service;

import com.clap.pos.products.dto.ProductDto;
import com.clap.pos.products.entities.Product;
import com.clap.pos.products.exception.ResourceNotFoundException;
import com.clap.pos.products.mapper.Mapper;
import com.clap.pos.products.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final Mapper mapper;

    public List<ProductDto> readAll() {
        return mapper.convertList((List<Product>) productRepository.findAll(), ProductDto.class);
    }

    public void create(ProductDto product) {
        productRepository.save(mapper.convert(product, Product.class));
    }

    public void update(ProductDto product, Integer productId) {
        var productFromDb = findByProductId(productId);
        mapper.convert(product, productFromDb);
        productRepository.save(productFromDb);
    }

    public void delete(Integer productId) {
        productRepository.delete(findByProductId(productId));
    }

    public ProductDto findByProductIdCasted(Integer productId) {
        return mapper.convert(findByProductId(productId), ProductDto.class);
    }

    public Product findByProductId(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + productId));
    }


}
