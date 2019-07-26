package com.coinf.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum StructureBonusType {

    ADJ_TUNNEL(StructureBonusType.getForAdjTunnel()),
    ADJ_LAKE(StructureBonusType.getForAdjLake()),
    ADJ_ENCOUNTER(StructureBonusType.getForAdjEncounter()),
    ON_TUNNEL(StructureBonusType.getForOnTunnel()),
    STRAIGHT_LINE(StructureBonusType.getForStraightLine()),
    ON_TUNDRA_OR_FARM(StructureBonusType.getForOnTundraOrFarm());

    private Map<String, String> earnedCoinsByResult;

    StructureBonusType(Map<String, String> earnedCoinsByResult) {
        this.earnedCoinsByResult = earnedCoinsByResult;
    }

    public Map<String, String> getEarnedCoinsByResult() {
        return earnedCoinsByResult;
    }

    private static Map<String, String> getForAdjTunnel() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "4");
        map.put("3", "4");
        map.put("4", "6");
        map.put("5", "6");
        map.put("6", "9");
        return map;
    }

    private static Map<String, String> getForAdjLake() {
        Map<String, String> map = getForAdjTunnel();
        map.put("7", "9");
        return map;
    }

    private static Map<String, String> getForAdjEncounter() {
        return getForAdjLake();
    }

    private static Map<String, String> getForOnTunnel() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "4");
        map.put("3", "6");
        map.put("4", "6");
        return map;
    }

    private static Map<String, String> getForStraightLine() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "4");
        map.put("3", "6");
        map.put("4", "9");
        return map;
    }

    private static Map<String, String> getForOnTundraOrFarm() {
        return getForStraightLine();
    }

}
