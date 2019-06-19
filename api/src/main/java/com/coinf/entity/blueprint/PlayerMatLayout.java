package com.coinf.entity.blueprint;

import com.coinf.entity.enums.PlayerMatLayoutType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Integer objectiveCards;
    private Integer popularity;
    private Integer coins;

    @OneToMany(mappedBy = "playerMatLayout",
            cascade = CascadeType.ALL)
    @OrderColumn(name = "position")
    private List<PlayerMatSectionParent> playerMatSections;

}
