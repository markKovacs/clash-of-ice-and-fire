package com.coinf.entity.instance;

import com.coinf.entity.blueprint.StructureBonus;
import com.coinf.entity.converter.CommaSepToIntListConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Hex> hexes = new ArrayList<>();

    @ManyToOne(optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "structure_bonus_id")
    private StructureBonus structureBonus;

    @Column(nullable = false)
    @Convert(converter = CommaSepToIntListConverter.class)
    private List<Integer> combatCards;

    @Column(nullable = false)
    @Convert(converter = CommaSepToIntListConverter.class)
    private List<Integer> encounterCards;

    @Column(nullable = false)
    @Convert(converter = CommaSepToIntListConverter.class)
    private List<Integer> factoryCards;

    @OneToOne(mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private PowerDial attacker;

    @OneToOne(mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private PowerDial defender;

    @OneToOne(mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private TriumphTrack triumphTrack;

}
