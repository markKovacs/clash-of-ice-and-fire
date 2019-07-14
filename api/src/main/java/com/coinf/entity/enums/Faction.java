package com.coinf.entity.enums;

import com.coinf.entity.blueprint.HexNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Faction {

    TARGARYEN(Arrays.asList(HexType.VILLAGE, HexType.FARM)),
    LANNISTER(Arrays.asList(HexType.FOREST, HexType.MOUNTAIN)),
    STARK(Arrays.asList(HexType.FOREST, HexType.MOUNTAIN)),
    BARATHEON(Arrays.asList(HexType.FOREST, HexType.MOUNTAIN, HexType.TUNDRA, HexType.VILLAGE, HexType.FARM)),
    GREYJOY(Arrays.asList(HexType.VILLAGE, HexType.MOUNTAIN)),
    TYRELL(Arrays.asList(HexType.TUNDRA, HexType.FARM)),
    WHITE_WALKER(Collections.emptyList());

    private final List<HexType> destinations;

    Faction(List<HexType> destinations) {
        this.destinations = destinations;
    }

    public boolean canRiverwalk(HexNode from, HexNode dest, int riversCrossed) {
        if (this.equals(WHITE_WALKER) && (from.isTunnel() || dest.isTunnel())) {
            return true;
        }
        if (this.equals(BARATHEON) && riversCrossed <= 0) {
            return true;
        }
        return this.destinations.contains(dest.getHexType());
    }

}
