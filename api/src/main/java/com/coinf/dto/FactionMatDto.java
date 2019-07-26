package com.coinf.dto;

import com.coinf.entity.enums.Faction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FactionMatDto {

    // BLUEPRINT
    private Faction faction;
    private int power;
    private int combatCards;

    private String heroName;
    private String houseName;

    private String mech1Name;
    private String mech2Name;
    private String mech3Name;
    private String mech4Name;

    private String mech1Desc;
    private String mech2Desc;
    private String mech3Desc;
    private String mech4Desc;

    private String specName;
    private String specDesc;

    // ACTUAL FACTION MAT
    private boolean powerUsed;
    private boolean coinUsed;
    private boolean popularityUsed;
    private boolean combatCardUsed;

    private boolean mech1Deployed;
    private boolean mech2Deployed;
    private boolean mech3Deployed;
    private boolean mech4Deployed;

}
