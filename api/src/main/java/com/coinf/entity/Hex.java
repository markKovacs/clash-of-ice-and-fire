package com.coinf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Hex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne(mappedBy = "hex",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Building building;

    @OneToMany(mappedBy = "hex",
            cascade = CascadeType.ALL)
    private List<Unit> units;

    private boolean encounterUsed;

    private Integer oil;
    private Integer food;
    private Integer steel;
    private Integer wood;

    private Long hexNodeId;

    /**
     * To be populated based on hexNodeId by an enricher in the cache.
     * This way circular references are avoided when loading a game instance.
     */
    @Transient
    private HexNode hexNode;

}
