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
            cascade = CascadeType.ALL)
    @OrderColumn(name = "position")
    private List<PlayerMatSectionParent> playerMatSections = new ArrayList<>();

    public static PlayerMatLayout ofType(PlayerMatLayoutType type, List<PlayerMatSectionParent> sections) {
        switch (type) {
            case MILITANT: return new PlayerMatLayout(PlayerMatLayoutType.MILITANT, sections);
            case INDUSTRIAL: return new PlayerMatLayout(PlayerMatLayoutType.INDUSTRIAL, sections);
            case INNOVATIVE: return new PlayerMatLayout(PlayerMatLayoutType.INNOVATIVE, sections);
            case ENGINEERING: return new PlayerMatLayout(PlayerMatLayoutType.ENGINEERING, sections);
            case MECHANICAL: return new PlayerMatLayout(PlayerMatLayoutType.MECHANICAL, sections);
            case AGRICULTURAL: return new PlayerMatLayout(PlayerMatLayoutType.AGRICULTURAL, sections);
            case PATRIOTIC: return new PlayerMatLayout(PlayerMatLayoutType.PATRIOTIC, sections);
            default: throw new IllegalArgumentException("Type " + type + " does not exist.");
        }
    }

    private PlayerMatLayout(PlayerMatLayoutType type, List<PlayerMatSectionParent> sections) {
        this.playerMatLayoutType = type;
        this.startOrder = type.order;
        this.objectives = type.objectives;
        this.popularity = type.popularity;
        this.coins = type.coins;

        addSections(sections);
    }

    private void addSections(List<PlayerMatSectionParent> sections) {
        if (playerMatSections == null) {
            playerMatSections = new ArrayList<>();
        }
        for (PlayerMatSectionParent section : sections) {
            playerMatSections.add(section);
            section.setPlayerMatLayout(this);
        }
    }

}
