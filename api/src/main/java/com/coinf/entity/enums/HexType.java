package com.coinf.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum HexType {

    BLANK,
    IRONTHRONE,
    LAKE,
    TUNDRA,
    MOUNTAIN,
    VILLAGE,
    FOREST,
    FARM,

    TARGARYEN,
    LANNISTER,
    STARK,
    BARATHEON,
    GREYJOY,
    MARTELL,
    WHITE_WALKER;

    public static final List<HexType> HOME_BASES = Arrays.asList(
            TARGARYEN, LANNISTER, STARK, BARATHEON, GREYJOY, MARTELL, WHITE_WALKER);

    public static final List<HexType> PRIMARY_TERRAINS = Arrays.asList(
            TUNDRA, MOUNTAIN, VILLAGE, FOREST, FARM);

    public static HexType fromFaction(Faction faction) {
        if (faction == null) {
            throw new IllegalArgumentException("Faction cannot be null");
        }
        return HexType.valueOf(faction.name());
    }

}
