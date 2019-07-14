package com.coinf.entity.instance;

import com.coinf.entity.blueprint.FactionMatLayout;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class FactionMat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(optional = false)
    @JoinColumn(name="player_mat_layout_id")
    private FactionMatLayout factionMatLayout;

    @Column(nullable = false)
    private boolean powerUsed;
    @Column(nullable = false)
    private boolean coinUsed;
    @Column(nullable = false)
    private boolean popularityUsed;
    @Column(nullable = false)
    private boolean combatCardUsed;

    @Column(nullable = false)
    private boolean mechOneDeployed;
    @Column(nullable = false)
    private boolean mechTwoDeployed;
    @Column(nullable = false)
    private boolean mechThreeDeployed;
    @Column(nullable = false)
    private boolean mechFourDeployed;

}
