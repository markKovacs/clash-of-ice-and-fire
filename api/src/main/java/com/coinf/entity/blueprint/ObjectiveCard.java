package com.coinf.entity.blueprint;

import com.coinf.entity.instance.Game;
import com.coinf.entity.instance.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.function.BiPredicate;

@Data
@NoArgsConstructor
@Entity
public class ObjectiveCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer cardNumber;

    private String title;

    private String description;

    /**
     * This field is added by ObjectiveCardEnricher after init but before caching.
     */
    @Transient
    private BiPredicate<Game, Player> isCompleted;

    public ObjectiveCard(Integer cardNumber, String title, String description) {
        this.cardNumber = cardNumber;
        this.title = title;
        this.description = description;
    }

}
