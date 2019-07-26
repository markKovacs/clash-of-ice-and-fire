package com.coinf.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class CommaSepToIntListConverter implements AttributeConverter<List<Integer>, String> {

    @Override
    public String convertToDatabaseColumn(List<Integer> integerList) {
        return integerList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Integer> convertToEntityAttribute(String commaSepString) {
        return Arrays.stream(commaSepString.split(","))
                .filter(n -> !n.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
