package com.coinf.entity.instance;

import com.coinf.entity.enums.Faction;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @Enumerated(EnumType.STRING)
    private Faction faction;

    @Column(nullable = false)
    private boolean powerUsed;
    @Column(nullable = false)
    private boolean coinUsed;
    @Column(nullable = false)
    private boolean popularityUsed;
    @Column(nullable = false)
    private boolean combatCardUsed;

    @Column(nullable = false)
    private boolean mech1Deployed;
    @Column(nullable = false)
    private boolean mech2Deployed;
    @Column(nullable = false)
    private boolean mech3Deployed;
    @Column(nullable = false)
    private boolean mech4Deployed;

    public static FactionMat of(Faction faction) {
        return new FactionMat(faction);
    }

    private FactionMat(Faction faction) {
        this.faction = faction;
    }

    public boolean deployedOnce() {
        return mech1Deployed || mech2Deployed || mech3Deployed || mech4Deployed;
    }

    public int mechCount() {
        int result = 0;
        if (mech1Deployed) result++;
        if (mech2Deployed) result++;
        if (mech3Deployed) result++;
        if (mech4Deployed) result++;
        return result;
    }

}
