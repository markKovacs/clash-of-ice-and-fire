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

    @Column(nullable = false)
    private Integer power;
    @Column(nullable = false)
    private Integer combatCards;

    @Column(nullable = false)
    private String charactersName;
    @Column(nullable = false)
    private String countryName;

    @Column(nullable = false)
    private String mechSkillNameOne;
    @Column(nullable = false)
    private String mechSkillNameTwo;
    @Column(nullable = false)
    private String mechSkillNameThree;
    @Column(nullable = false)
    private String mechSkillNameFour;

    @Column(nullable = false)
    private String mechSkillDescOne;
    @Column(nullable = false)
    private String mechSkillDescTwo;
    @Column(nullable = false)
    private String mechSkillDescThree;
    @Column(nullable = false)
    private String mechSkillDescFour;

    @Column(nullable = false)
    private String factionSpecialSkillName;
    @Column(nullable = false)
    private String factionSpecialSkillDesc;

}
