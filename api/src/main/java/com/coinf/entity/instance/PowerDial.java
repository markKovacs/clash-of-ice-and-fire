package com.coinf.entity.instance;

import com.coinf.entity.converter.CommaSepToIntListConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class PowerDial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(nullable = false)
    @Convert(converter = CommaSepToIntListConverter.class)
    private List<Integer> combatCards = new ArrayList<>();

    @Column(nullable = false)
    private int power;

    @Column(nullable = false)
    private boolean isAttacker;

    @Column(nullable = false)
    private boolean isReady;

    public PowerDial(boolean isAttacker) {
        this.isAttacker = isAttacker;
    }

    public void reset() {
        combatCards.clear();
        power = 0;
        isReady = false;
    }

}
