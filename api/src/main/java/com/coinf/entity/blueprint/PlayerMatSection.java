package com.coinf.entity.blueprint;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class PlayerMatSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_mat_layout_id")
    private PlayerMatLayout playerMatLayout;

    private int position;

    @OneToOne(mappedBy = "playerMatSection",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false)
    private BottomRowAction bottomRowAction;

    @OneToOne(mappedBy = "playerMatSection",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false)
    private TopRowAction topRowAction;

    public PlayerMatSection(BottomRowAction bottomRowAction, TopRowAction topRowAction) {
        this.position = bottomRowAction.getBottomRowActionType().ordinal();
        this.bottomRowAction = bottomRowAction;
        this.topRowAction = topRowAction;

        // MAP BIDIRECTIONALLY
        bottomRowAction.setPlayerMatSection(this);
        topRowAction.setPlayerMatSection(this);
    }

}
