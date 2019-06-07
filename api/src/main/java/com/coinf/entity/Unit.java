package com.coinf.entity;

import com.coinf.entity.enums.UnitType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hex_id")
    private Hex hex;

    @Enumerated(EnumType.STRING)
    private UnitType buildingType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id")
    private Player player;

}
