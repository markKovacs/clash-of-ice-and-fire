package com.coinf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class GameDto {

    private Long id;
    private List<HexDto> hexes;
    private Map<String, Integer> popularityByUser;
    private Map<String, Integer> powerByUser;
    private StructureBonusDto structureBonusDto;
    private List<OpponentDto> opponents;
    private PlayerDto player;
    private List<StarDto> stars;

}
