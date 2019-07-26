package com.coinf.dto;

import com.coinf.entity.enums.PlayerMatLayoutType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PlayerMatDto {

    private Integer currentSection;

    // BLUEPRINT
    private List<PlayerMatSectionDto> sections;
    private PlayerMatLayoutType type;
    private int startOrder;
    private int objectives;
    private int popularity;
    private int coins;

    // TOP ROW ACTUAL
    private int workers;
    private boolean produceUpgraded;
    private boolean millBuilt;

    private boolean tradeUpgraded;
    private boolean armoryBuilt;

    private boolean bolsterPowerUpgraded;
    private boolean bolsterCardUpgraded;
    private boolean monumentBuilt;

    private boolean moveUpgraded;
    private boolean gainUpgraded;
    private boolean mineBuilt;

    // BOTTOM ROW ACTUAL
    private int upgradeUpgraded;
    private int deployUpgraded;
    private int buildUpgraded;
    private int enlistUpgraded;

    private boolean upgradeEnlisted;
    private boolean deployEnlisted;
    private boolean buildEnlisted;
    private boolean enlistEnlisted;

}
