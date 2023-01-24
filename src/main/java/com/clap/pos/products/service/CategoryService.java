package com.clap.pos.products.service;

import com.clap.pos.products.dto.CategoryDto;
import com.clap.pos.products.entities.Category;
import com.clap.pos.products.exception.ResourceNotFoundException;
import com.clap.pos.products.mapper.Mapper;
import com.clap.pos.products.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;

    private final Mapper mapper;

    public List<CategoryDto> readAll() {
        return mapper.convertList((List<Category>) categoryRepository.findAll(), CategoryDto.class);
    }

    public void create(CategoryDto category) {
        categoryRepository.save(mapper.convert(category, Category.class));
    }

    public void update(CategoryDto category, Integer categoryId) {
        Optional<Category> categoryFromDb = categoryRepository.findById(categoryId);
        categoryFromDb.ifPresent(category1 -> {
            category1.setCategoryDescription(category.getCategoryDescription());
            category1.setCategoryName(category.getCategoryName());
            categoryRepository.save(category1);
        });
    }

    public void delete(Integer categoryId) {
        Optional<Category> categoryFromDb = categoryRepository.findById(categoryId);
        categoryFromDb.ifPresent(categoryRepository::delete);
    }

    public CategoryDto findCategoryByIdCasted(Integer categoryId) {
        return mapper.convert(findByCategoryId(categoryId), CategoryDto.class);
    }

    private Category findByCategoryId(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Not found category with id = " + categoryId));
    }
}
