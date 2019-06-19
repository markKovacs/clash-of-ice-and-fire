package com.coinf.entity.blueprint;

import com.coinf.entity.enums.Direction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Edge {

    @Id
    @ManyToOne(fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(referencedColumnName = "id")
    private HexNode source;

    @Id
    @ManyToOne(fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(referencedColumnName = "id")
    private HexNode destination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Direction direction;

    private Boolean hasRiver;

}
