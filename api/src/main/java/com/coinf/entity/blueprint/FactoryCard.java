package com.coinf.entity.blueprint;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class FactoryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "playerMatSection",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false)
    private FactoryTopRowAction topRowAction;

    private int cardNum;

    public FactoryCard(FactoryTopRowAction factoryTopRowAction, int cardNum) {
        this.topRowAction = factoryTopRowAction;
        this.cardNum = cardNum;

        // MAP BIDIRECTIONALLY
        factoryTopRowAction.setPlayerMatSection(this);
    }

}
