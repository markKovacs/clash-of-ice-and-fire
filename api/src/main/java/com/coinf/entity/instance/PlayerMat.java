package com.coinf.entity.instance;

import com.coinf.entity.blueprint.PlayerMatLayout;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class PlayerMat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name="player_mat_layout_id")
    private PlayerMatLayout playerMatLayout;

    @Column(nullable = false)
    private Integer currentSectionIndex;

    // top row
    @Column(nullable = false)
    private Integer workers;
    @Column(nullable = false)
    private boolean produceUpgraded;
    @Column(nullable = false)
    private boolean millBuilt;

    @Column(nullable = false)
    private boolean tradeUpgraded;
    @Column(nullable = false)
    private boolean armoryBuilt;

    @Column(nullable = false)
    private boolean bolsterPowerUpgraded;
    @Column(nullable = false)
    private boolean bolsterCardUpgraded;
    @Column(nullable = false)
    private boolean monumentBuilt;

    @Column(nullable = false)
    private boolean moveUpgraded;
    @Column(nullable = false)
    private boolean gainUpgraded;
    @Column(nullable = false)
    private boolean mineBuilt;

    // bottom row
    @Column(nullable = false)
    private Integer upgradedUpgrade;
    @Column(nullable = false)
    private Integer upgradedDeploy;
    @Column(nullable = false)
    private Integer upgradedBuild;
    @Column(nullable = false)
    private Integer upgradedEnlist;

    @Column(nullable = false)
    private boolean upgradeEnlisted;
    @Column(nullable = false)
    private boolean deployEnlisted;
    @Column(nullable = false)
    private boolean buildEnlisted;
    @Column(nullable = false)
    private boolean enlistEnlisted;

}
