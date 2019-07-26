package com.coinf.entity.blueprint;

import com.coinf.entity.enums.PlayerMatLayoutType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class PlayerMatLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlayerMatLayoutType playerMatLayoutType;

    private Integer startOrder;
    private Integer objectives;
    private Integer popularity;
    private Integer coins;

    @OneToMany(mappedBy = "playerMatLayout",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @OrderColumn(name = "position")
    private List<PlayerMatSection> playerMatSections = new ArrayList<>();

    public static PlayerMatLayout ofType(PlayerMatLayoutType type, List<PlayerMatSection> sections) {
        return new PlayerMatLayout(type, sections);
    }

    private PlayerMatLayout(PlayerMatLayoutType type, List<PlayerMatSection> sections) {
        this.playerMatLayoutType = type;
        this.startOrder = type.order;
        this.objectives = type.objectives;
        this.popularity = type.popularity;
        this.coins = type.coins;

        addSections(sections);
    }

    private void addSections(List<PlayerMatSection> sections) {
        if (playerMatSections == null) {
            playerMatSections = new ArrayList<>();
        }
        for (PlayerMatSection section : sections) {
            playerMatSections.add(section);

            // MAP BIDIRECTIONALLY
            section.setPlayerMatLayout(this);
        }
    }

}
