package com.coinf.entity.instance;

import com.coinf.entity.blueprint.GameEvent;
import com.coinf.entity.blueprint.StructureBonus;
import com.coinf.entity.converter.CommaSepToIntListConverter;
import com.coinf.entity.enums.GameState;
import com.coinf.entity.enums.StructureBonusType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Game {

    // TODO: Remove units and buildings if looks like not needed

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

    @OneToMany(mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Unit> units = new ArrayList<>();

    @OneToMany(mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Building> buildings = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StructureBonusType structureBonusType;

    @Column(nullable = false)
    @Convert(converter = CommaSepToIntListConverter.class)
    private List<Integer> combatCards = new ArrayList<>();

    @Column(nullable = false)
    @Convert(converter = CommaSepToIntListConverter.class)
    private List<Integer> encounterCards = new ArrayList<>();

    @Column(nullable = false)
    @Convert(converter = CommaSepToIntListConverter.class)
    private List<Integer> factoryCards = new ArrayList<>();

    @OneToMany(mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<PowerDial> powerDials = new ArrayList<>();

    @OneToOne(mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private TriumphTrack triumphTrack;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "game_game_event",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<GameEvent> eventsThisTurn = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private GameState gameState;

    @OneToOne(fetch = FetchType.LAZY)
    private Player currentPlayer;


    public Game(List<Player> players,
                List<Hex> hexes,
                List<Integer> combatCards,
                StructureBonusType chosenStructBonus,
                List<Integer> factoryCards,
                List<Integer> encounterCards,
                PowerDial attacker,
                PowerDial defender,
                TriumphTrack triumphTrack,
                Player currentPlayer) {
        this.players = players;
        this.hexes = hexes;
        this.combatCards = combatCards;
        this.structureBonusType = chosenStructBonus;
        this.factoryCards = factoryCards;
        this.encounterCards = encounterCards;
        this.powerDials.addAll(Arrays.asList(attacker, defender));
        this.triumphTrack = triumphTrack;
        this.gameState = GameState.ACTION_PICKING;
        this.currentPlayer = currentPlayer;

        // BIDIRECTIONAL SETTINGS
        for (Player player : players) {
            player.setGame(this);
        }
        for (Hex hex : hexes) {
            hex.setGame(this);
        }
        attacker.setGame(this);
        defender.setGame(this);
        triumphTrack.setGame(this);
    }

    public void addEvent(GameEvent event) {
        eventsThisTurn.add(event);
        event.getGames().add(this);
    }

    public void resetEvents() {
        for (GameEvent event : eventsThisTurn) {
            event.getGames().clear();
        }
        eventsThisTurn.clear();
    }

    public boolean turnEnded() {
        return gameState.equals(GameState.TURN_ENDED);
    }

    public PowerDial getAttacker() {
        for (PowerDial powerDial : powerDials) {
            if (powerDial.isAttacker()) {
                return powerDial;
            }
        }
        throw new IllegalStateException("Attacker power dial was not properly initialized.");
    }

    public PowerDial getDefender() {
        for (PowerDial powerDial : powerDials) {
            if (!powerDial.isAttacker()) {
                return powerDial;
            }
        }
        throw new IllegalStateException("Defender power dial was not properly initialized.");
    }

}
