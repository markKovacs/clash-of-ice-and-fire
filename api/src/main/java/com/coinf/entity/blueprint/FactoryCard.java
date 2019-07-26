package com.coinf.entity.blueprint;

import com.coinf.entity.instance.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class FactoryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "factoryCard",
            cascade = CascadeType.ALL)
    private List<Player> players;

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
