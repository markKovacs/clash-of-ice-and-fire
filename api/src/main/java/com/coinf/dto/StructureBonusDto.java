package com.coinf.dto;

import com.coinf.entity.enums.StructureBonusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class StructureBonusDto {

    private StructureBonusType type;
    private Map<String, String> coinsByResult;

}
