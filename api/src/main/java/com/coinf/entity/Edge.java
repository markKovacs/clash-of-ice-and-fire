package com.coinf.entity;

import com.coinf.entity.enums.Direction;
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

    @Enumerated(EnumType.STRING)
    private Direction direction;

    private boolean hasRiver;

}
