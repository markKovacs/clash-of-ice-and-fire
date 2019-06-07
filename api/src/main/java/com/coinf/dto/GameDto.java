package com.coinf.dto;

import java.util.List;

public class GameDto {
    // TODO: Add builder and other lombok stuff
    private List<HexDto> hexes;
    private List<UnitDto> units;
    private List<PlayerDto> opponents;
    private PlayerDto player;
}
