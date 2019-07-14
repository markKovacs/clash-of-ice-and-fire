package com.coinf.entity.blueprint;

import com.coinf.entity.enums.Direction;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(referencedColumnName = "id")
    private HexNode source;

    @ManyToOne(fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(referencedColumnName = "id")
    private HexNode destination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Direction direction;

    @Getter(AccessLevel.NONE)
    private boolean hasRiver;

    public Boolean hasRiver() {
        return hasRiver;
    }

    public Edge(HexNode source, HexNode destination, Direction direction, Boolean hasRiver) {
        this.source = source;
        this.destination = destination;
        this.direction = direction;
        this.hasRiver = hasRiver;
    }

}
