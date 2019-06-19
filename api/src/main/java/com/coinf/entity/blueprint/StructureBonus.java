package com.coinf.entity.blueprint;

import com.coinf.entity.enums.StructureBonusType;
import com.coinf.entity.instance.Game;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
@Entity
public class StructureBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "structureBonus", cascade = CascadeType.ALL)
    private List<Game> games;

    @Enumerated(EnumType.STRING)
    private StructureBonusType type;

    @ElementCollection
    @MapKeyColumn(name="result")
    @Column(name="coins")
    @CollectionTable(name="result_coins", joinColumns=@JoinColumn(name="result_coins_id"))
    private Map<String, String> earnedCoinsByResult;

}
