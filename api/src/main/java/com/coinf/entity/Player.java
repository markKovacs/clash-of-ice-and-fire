package com.coinf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Holds player details for a specific account if user is in a game.
 */
@Data
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Account account;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL)
    private List<Building> buildings;

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL)
    private List<Unit> units;

    // TODO: map corresponding player and faction mat instances

}
