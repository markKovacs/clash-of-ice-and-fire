package com.coinf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ObjectiveCardDto {

    private Integer cardNumber;
    private String title;
    private String description;

}
