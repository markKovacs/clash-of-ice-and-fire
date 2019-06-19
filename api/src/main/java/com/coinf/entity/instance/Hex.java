package com.coinf.entity.instance;

import com.coinf.entity.blueprint.HexNode;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Hex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne(mappedBy = "hex",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Building building;

    @OneToMany(mappedBy = "hex",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Unit> units = new ArrayList<>();

    // null if does not even have encounter
    @Column(nullable = true)
    private Boolean encounterUsed;

    @Column(nullable = false)
    private Integer oil;
    @Column(nullable = false)
    private Integer food;
    @Column(nullable = false)
    private Integer steel;
    @Column(nullable = false)
    private Integer wood;

    @Column(nullable = false)
    private Long hexNodeId;

    /**
     * To be populated based on hexNodeId by an enricher in the cache.
     * This way circular references are avoided when loading a game instance.
     */
    @Transient
    private HexNode hexNode;

}
