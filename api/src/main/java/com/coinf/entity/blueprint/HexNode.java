package com.coinf.entity.blueprint;

import com.coinf.entity.enums.Direction;
import com.coinf.entity.enums.HexType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private boolean encounter;
    private boolean isTunnel;
    private String name;
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

    public static HexNode getBlankInstance(Integer weight) {
        return new HexNode(HexType.BLANK, weight, false, false, null, null);
    }

    public static HexNode getInstance(HexType hexType, Integer weight, Boolean hasEncounter, Boolean isTunnel, String name, String description) {
        return new HexNode(hexType, weight, hasEncounter, isTunnel, name, description);
    }

    private HexNode(HexType hexType, Integer weight, boolean hasEncounter, boolean isTunnel,
                   String name, String description) {
        this.hexType = hexType;
        this.weight = weight;
        this.encounter = hasEncounter;
        this.isTunnel = isTunnel;
        this.name = name;
        this.description = description;
    }

    @Override
    public int compareTo(HexNode o) {
        return this.weight.compareTo(o.weight);
    }

    public List<HexNode> getAdjacentNodes() {
        return neighbours.values()
                .stream()
                .map(Edge::getDestination)
                .collect(Collectors.toList());
    }

    public boolean ofType(HexType type) {
        return hexType.equals(type);
    }

}
