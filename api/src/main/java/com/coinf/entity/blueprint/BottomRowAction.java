package com.coinf.entity.blueprint;

import com.coinf.entity.enums.BottomRowActionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class BottomRowAction {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @MapsId
    private PlayerMatSection playerMatSection;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BottomRowActionType bottomRowActionType;

    private int fixedPayment;
    private int reducablePayment;
    private int gainableCoins;

    public BottomRowAction(BottomRowActionType bottomRowActionType, int fixedPayment, int reducablePayment, int gainableCoins) {
        this.bottomRowActionType = bottomRowActionType;
        this.fixedPayment = fixedPayment;
        this.reducablePayment = reducablePayment;
        this.gainableCoins = gainableCoins;
    }

}
