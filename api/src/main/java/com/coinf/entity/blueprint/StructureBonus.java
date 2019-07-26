package com.coinf.entity.blueprint;

import com.coinf.entity.enums.StructureBonusType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
@Entity
public class StructureBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StructureBonusType type;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name="result")
    @Column(name="coins")
    @CollectionTable(name="result_coins", joinColumns=@JoinColumn(name="result_coins_id"))
    private Map<String, String> earnedCoinsByResult = new HashMap<>();

    public static StructureBonus of(StructureBonusType type) {
        return new StructureBonus(type);
    }

    private StructureBonus(StructureBonusType type) {
        this.type = type;
        this.earnedCoinsByResult = type.getEarnedCoinsByResult();
    }

    public boolean ofType(StructureBonusType type) {
        return this.type.equals(type);
    }

}
