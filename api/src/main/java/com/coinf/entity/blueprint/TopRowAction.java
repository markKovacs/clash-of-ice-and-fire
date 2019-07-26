package com.coinf.entity.blueprint;

import com.coinf.entity.enums.TopRowActionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class TopRowAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TopRowActionType topRowActionType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_mat_section_id")
    private PlayerMatSection playerMatSection;

    public TopRowAction(TopRowActionType topRowActionType) {
        this.topRowActionType = topRowActionType;
    }

}
