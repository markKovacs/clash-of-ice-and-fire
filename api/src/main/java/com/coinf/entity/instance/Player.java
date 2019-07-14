package com.coinf.entity.instance;

import com.coinf.entity.blueprint.ObjectiveCard;
import com.coinf.entity.converter.CommaSepToIntListConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds player details for a specific account if user is in a game.
 */
@Data
@NoArgsConstructor
@Entity
public class Player {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @MapsId
    private Account account;

    @ManyToOne(optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Building> buildings = new ArrayList<>();

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Unit> units = new ArrayList<>();

    @OneToOne(mappedBy = "player",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
    private PlayerMat playerMat;

    @OneToOne(mappedBy = "player",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
    private FactionMat factionMat;

    @Column(nullable = false)
    @Convert(converter = CommaSepToIntListConverter.class)
    private List<Integer> combatCards = new ArrayList<>();

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Star> stars = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "player_objective_card",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "objective_card_id"))
    private List<ObjectiveCard> objectives = new ArrayList<>();

    @Column(nullable = false)
    private Integer power;

    @Column(nullable = false)
    private Integer popularity;

    @Column(nullable = false)
    private Integer coin;

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Trap> traps = new ArrayList<>();

    private Integer flagsAvailable;

}
