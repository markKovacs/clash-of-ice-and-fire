package com.coinf.entity.blueprint;

import com.coinf.entity.enums.Faction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class FactionMatLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Faction faction;

    private int power;
    private int combatCards;

    @Column(nullable = false)
    private String heroName;
    @Column(nullable = false)
    private String houseName;

    @Column(nullable = false)
    private String mech1Name;
    @Column(nullable = false)
    private String mech2Name;
    @Column(nullable = false)
    private String mech3Name;
    @Column(nullable = false)
    private String mech4Name;

    @Column(nullable = false)
    private String mech1Desc;
    @Column(nullable = false)
    private String mech2Desc;
    @Column(nullable = false)
    private String mech3Desc;
    @Column(nullable = false)
    private String mech4Desc;

    @Column(nullable = false)
    private String specName;
    @Column(nullable = false)
    private String specDesc;

    public static FactionMatLayout ofType(Faction faction) {
        return new FactionMatLayout(faction);
    }

    private FactionMatLayout(Faction faction) {
        this.faction = faction;
        this.power = faction.power;
        this.combatCards = faction.combatCards;
        this.heroName = faction.heroName;
        this.houseName = faction.houseName;
        this.mech1Name = faction.mech1Name;
        this.mech2Name = faction.mech2Name;
        this.mech3Name = faction.mech3Name;
        this.mech4Name = faction.mech4Name;
        this.mech1Desc = faction.mech1Desc;
        this.mech2Desc = faction.mech2Desc;
        this.mech3Desc = faction.mech3Desc;
        this.mech4Desc = faction.mech4Desc;
        this.specName = faction.specName;
        this.specDesc = faction.specDesc;
    }

}
