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
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @MapsId
    private Player player;

    @ManyToOne(optional = false)
    @JoinColumn(name="player_mat_layout_id")
    private FactionMatLayout playerMatLayout;

    @Column(nullable = false)
    private Boolean powerUsed;
    @Column(nullable = false)
    private Boolean coinUsed;
    @Column(nullable = false)
    private Boolean popularityUsed;
    @Column(nullable = false)
    private Boolean combatCardUsed;

    @Column(nullable = false)
    private Boolean mechOneUsed;
    @Column(nullable = false)
    private Boolean mechTwoUsed;
    @Column(nullable = false)
    private Boolean mechThreeUsed;
    @Column(nullable = false)
    private Boolean mechFourUsed;

}
