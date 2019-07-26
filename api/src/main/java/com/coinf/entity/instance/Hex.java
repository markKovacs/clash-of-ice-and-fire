package com.coinf.entity.instance;

import com.coinf.entity.blueprint.HexNode;
import com.coinf.util.CollectionUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hex", indexes = {
        @Index(columnList = "game_id,hexNodeId", name = "game_id_hex_node_id_hidx")
})
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

    private Boolean encounterUsed;

    private int oil;
    private int food;
    private int steel;
    private int wood;

    private boolean flagged;

    @OneToOne(mappedBy = "hex",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Trap trap;

    @Column(nullable = false)
    private Long hexNodeId;

    public Hex(HexNode hexNode) {
        encounterUsed = hexNode.isEncounter() ? false : null;
        hexNodeId = hexNode.getId();
    }

    public int getSumOfResources() {
        return oil + food + steel + wood;
    }

    public boolean hasAllDistinctResources() {
        return oil > 0 && food > 0 && steel > 0 && wood > 0;
    }

    public int getSumOfTokensForPlayer(Player player) {
        int count = (int) units
                .stream()
                .filter(unit -> unit.getPlayer().equals(player))
                .count();
        return count + ((building != null && building.getPlayer().equals(player)) ? 1 : 0);
    }

    public boolean isControlledBy(Player player) {
        if (!CollectionUtils.isEmpty(units)) {
            return units.stream().allMatch(unit -> unit.getPlayer().equals(player));
        }
        return building != null && player.equals(building.getPlayer());
    }

    public boolean isUnoccupied() {
        return building == null && CollectionUtils.isEmpty(units);
    }

}
