package com.coinf.entity.instance;

import com.coinf.entity.blueprint.FactoryCard;
import com.coinf.entity.blueprint.ObjectiveCard;
import com.coinf.entity.converter.CommaSepToIntListConverter;
import com.coinf.entity.enums.Faction;
import com.coinf.entity.enums.UnitType;
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

    @Column(nullable = false)
    @Convert(converter = CommaSepToIntListConverter.class)
    private List<Integer> objectives = new ArrayList<>();

    // TODO: maybe this should be factory card id and look up from cache if the object needed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factory_card_id")
    private FactoryCard factoryCard;

    private int power;
    private int popularity;
    private int coins;

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Trap> traps = new ArrayList<>();

    private int flagsAvailable;

    public Player(Account account, PlayerMat playerMat, FactionMat factionMat,
                  List<Integer> combatCards, List<Integer> objectives,
                  int power, int popularity, int coins) {
        this.account = account;
        this.playerMat = playerMat;
        this.factionMat = factionMat;
        this.combatCards = combatCards;
        this.objectives = objectives;
        this.power = power;
        this.popularity = popularity;
        this.coins = coins;

        // BIDIRECTIONAL SETTING
        account.setPlayer(this);
        playerMat.setPlayer(this);
        factionMat.setPlayer(this);
    }

    public Faction getFaction() {
        return factionMat.getFaction();
    }

    public Unit createUnit(Hex hex, UnitType type) {
        Unit unit = new Unit(type);
        unit.setPlayer(this);
        this.units.add(unit);
        hex.addUnit(unit);
        return unit;
    }

    public Unit createUnit(Hex hex, UnitType type, Game game) {
        Unit unit = createUnit(hex, type);
        unit.setGame(game);
        return unit;
    }

}
