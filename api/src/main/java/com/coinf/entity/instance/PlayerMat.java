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
    private Boolean produceUpgraded;
    @Column(nullable = false)
    private Boolean millBuilt;

    @Column(nullable = false)
    private Boolean tradeUpgraded;
    @Column(nullable = false)
    private Boolean armoryBuilt;

    @Column(nullable = false)
    private Boolean bolsterPowerUpgraded;
    @Column(nullable = false)
    private Boolean bolsterCardUpgraded;
    @Column(nullable = false)
    private Boolean monumentBuilt;

    @Column(nullable = false)
    private Boolean moveUpgraded;
    @Column(nullable = false)
    private Boolean gainUpgraded;
    @Column(nullable = false)
    private Boolean mineBuilt;

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
    private Boolean upgradeEnlisted;
    @Column(nullable = false)
    private Boolean deployEnlisted;
    @Column(nullable = false)
    private Boolean buildEnlisted;
    @Column(nullable = false)
    private Boolean enlistEnlisted;

}
