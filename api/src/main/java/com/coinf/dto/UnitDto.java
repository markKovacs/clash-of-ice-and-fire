package com.coinf.dto;

import com.coinf.entity.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UnitDto {

    private Long id;
    private UnitType type;
    private String user;

}
