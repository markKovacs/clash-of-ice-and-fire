package com.coinf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
public class HexNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HexType hexType;

    private Integer weight;

    private boolean hasEncounter;

    private boolean isTunnel;

    private String shortName;

    private String prettyName;

    private String description;

    @Transient
    private Map<Direction, Edge> neighbours = new HashMap<>();

    public void addEdges(List<Edge> edges) {
        for (Edge edge : edges) {
            this.neighbours.put(edge.getDirection(), edge);
        }
    }

}
