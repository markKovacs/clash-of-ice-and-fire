package com.coinf.dto;

import com.coinf.entity.enums.BuildingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BuildingDto {

    private BuildingType buildingType;
    private String user;

}
