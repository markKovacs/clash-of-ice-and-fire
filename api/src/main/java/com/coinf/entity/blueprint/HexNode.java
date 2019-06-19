package com.coinf.entity.blueprint;

import com.coinf.entity.enums.Direction;
import com.coinf.entity.enums.HexType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
public class HexNode implements Comparable<HexNode> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HexType hexType;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private Boolean hasEncounter;

    @Column(nullable = false)
    private Boolean isTunnel;

    @Column(nullable = false)
    private String shortName;

    @Column(nullable = false)
    private String prettyName;

    @Column(nullable = false)
    private String description;

    @Transient
    private Map<Direction, Edge> neighbours = new HashMap<>();

    public void addEdges(List<Edge> edges) {
        if (edges == null) {
            throw new IllegalArgumentException("Edges parameter cannot be null.");
        }
        for (Edge edge : edges) {
            if (edge == null) {
                throw new IllegalArgumentException("An edge was null.");
            }
            this.neighbours.put(edge.getDirection(), edge);
        }
    }

    public HexNode(HexType hexType, Integer weight, Boolean hasEncounter, Boolean isTunnel,
                   String shortName, String prettyName, String description) {
        this.hexType = hexType;
        this.weight = weight;
        this.hasEncounter = hasEncounter;
        this.isTunnel = isTunnel;
        this.shortName = shortName;
        this.prettyName = prettyName;
        this.description = description;
    }

    @Override
    public int compareTo(HexNode o) {
        return o.weight - this.weight;
    }

}
