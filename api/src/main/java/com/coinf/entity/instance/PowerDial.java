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
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @MapsId
    private Game game;

    @Column(nullable = false)
    @Convert(converter = CommaSepToIntListConverter.class)
    private List<Integer> combatCards = new ArrayList<>();

    @Column(nullable = false)
    private Integer power;

    @Column(nullable = false)
    private Boolean isAttacker;

    @Column(nullable = false)
    private Boolean isReady;

}
