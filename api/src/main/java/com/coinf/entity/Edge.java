package com.coinf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Edge {

    @Id
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private HexNode source;

    @Id
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private HexNode destination;

    private boolean hasRiver;

    @Enumerated(EnumType.STRING)
    private Direction direction;

}
