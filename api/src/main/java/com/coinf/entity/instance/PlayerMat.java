package com.coinf.entity.instance;

import com.coinf.entity.enums.PlayerMatLayoutType;
import com.coinf.util.GameConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.coinf.util.GameConstants.SETUP_WORKERS;

@Data
@NoArgsConstructor
@Entity
public class PlayerMat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @Enumerated(EnumType.STRING)
    private PlayerMatLayoutType type;

    // NULL AT BEGINNING
    private Integer currentSectionIndex;

    // TOP ROW
    private int workersOnMat;
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

    // BOTTOM ROW
    private int upgradeUpgraded;
    private int deployUpgraded;
    private int buildUpgraded;
    private int enlistUpgraded;

    private boolean upgradeEnlisted;
    private boolean deployEnlisted;
    private boolean buildEnlisted;
    private boolean enlistEnlisted;

    public static PlayerMat of(PlayerMatLayoutType type) {
        return new PlayerMat(type, SETUP_WORKERS);
    }

    private PlayerMat(PlayerMatLayoutType type, int setupWorkers) {
        this.type = type;
        this.workersOnMat = setupWorkers;
    }
    
    public boolean enlistedOnce() {
        return upgradeEnlisted || deployEnlisted || buildEnlisted || enlistEnlisted;
    }

    public boolean upgradedOnce() {
        return upgradeUpgraded > 0 || deployUpgraded > 0 ||
                buildUpgraded > 0 || enlistUpgraded > 0 ||
                produceUpgraded || tradeUpgraded ||
                bolsterPowerUpgraded || bolsterCardUpgraded ||
                moveUpgraded || gainUpgraded;
    }

    public boolean builtOnce() {
        return millBuilt || armoryBuilt || mineBuilt || monumentBuilt;
    }

    public int recruitCount() {
        int result = 0;
        if (upgradeEnlisted) result++;
        if (deployEnlisted) result++;
        if (buildEnlisted) result++;
        if (enlistEnlisted) result++;
        return result;
    }

    public int workerCount() {
        return GameConstants.WORKERS_ONBOARD_START + (GameConstants.SETUP_WORKERS - workersOnMat);
    }

    public int buildingCount() {
        int result = 0;
        if (mineBuilt) result++;
        if (millBuilt) result++;
        if (armoryBuilt) result++;
        if (monumentBuilt) result++;
        return result;
    }

    public int upgradeCount() {
        int result = upgradeUpgraded + deployUpgraded + buildUpgraded + enlistUpgraded;
        if (produceUpgraded) result++;
        if (tradeUpgraded) result++;
        if (bolsterPowerUpgraded) result++;
        if (bolsterCardUpgraded) result++;
        if (moveUpgraded) result++;
        if (gainUpgraded) result++;
        return result;
    }

}
