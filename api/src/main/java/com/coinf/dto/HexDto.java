package com.coinf.dto;

import com.coinf.entity.enums.HexType;
import com.coinf.entity.enums.TrapType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Contains all information both about HexNode and Hex flattened
 */
@Data
@Builder
@AllArgsConstructor
public class HexDto {

    // ACTUAL HEX
    private Long hexId;
    private BuildingDto building;
    private List<UnitDto> units;
    private Boolean encounterUsed;
    private Integer oil;
    private Integer food;
    private Integer steel;
    private Integer wood;
    private boolean flagged;
    private TrapType trap;

    // HEX NODE BLUEPRINT
    private Long nodeId;
    private HexType hexType;
    private Integer weight;
    private boolean encounter;
    private boolean isTunnel;
    private String name;
    private String description;

}
