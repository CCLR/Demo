package com.clap.pos.products.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    public <T> T convert(Object source, Class<T> target) {
        return modelMapper().map(source, target);
    }

    public void convert(Object source, Object target) {
        modelMapper().map(source, target);
    }

    public <T> List<T> convertList(List<?> sources, Class<T> target) {
        return sources.stream().map(object -> convert(object, target)).collect(Collectors.toList());
    }
}



