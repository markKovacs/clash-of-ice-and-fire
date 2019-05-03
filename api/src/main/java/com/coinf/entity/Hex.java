package com.coinf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Hex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Game game;

    private Building building;

    private Boolean encounterUsed;

    private Integer oil;
    private Integer food;
    private Integer steel;
    private Integer wood;

    private Long hexNodeId;

    /**
     * To be populated from outside by an enricher via the BoardCache.
     */
    @Transient
    private HexNode hexNode;

}
