package com.coinf.entity.blueprint;

import com.coinf.entity.enums.GainType;
import com.coinf.entity.enums.LogicalRelation;
import com.coinf.entity.enums.PaymentType;
import com.coinf.entity.enums.TopRowActionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class FactoryTopRowAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TopRowActionType topRowActionType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_mat_section_id")
    private FactoryCard playerMatSection;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentTypeOne;
    private Integer paymentAmountOne;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentTypeTwo;
    private Integer paymentAmountTwo;

    @Enumerated(EnumType.STRING)
    private GainType gainTypeOne;
    private Integer gainAmountOne;

    @Enumerated(EnumType.STRING)
    private GainType gainTypeTwo;
    private Integer gainAmountTwo;

    @Enumerated(EnumType.STRING)
    private GainType gainTypeThree;
    private Integer gainAmountThree;

    @Enumerated(EnumType.STRING)
    private LogicalRelation logicalRelation;

    public FactoryTopRowAction(TopRowActionType topRowActionType,
                               PaymentType paymentTypeOne, Integer paymentAmountOne,
                               PaymentType paymentTypeTwo, Integer paymentAmountTwo,
                               GainType gainTypeOne, Integer gainAmountOne,
                               GainType gainTypeTwo, Integer gainAmountTwo,
                               GainType gainTypeThree, Integer gainAmountThree,
                               LogicalRelation logicalRelation) {
        this.topRowActionType = topRowActionType;
        this.paymentTypeOne = paymentTypeOne;
        this.paymentAmountOne = paymentAmountOne;
        this.paymentTypeTwo = paymentTypeTwo;
        this.paymentAmountTwo = paymentAmountTwo;
        this.gainTypeOne = gainTypeOne;
        this.gainAmountOne = gainAmountOne;
        this.gainTypeTwo = gainTypeTwo;
        this.gainAmountTwo = gainAmountTwo;
        this.gainTypeThree = gainTypeThree;
        this.gainAmountThree = gainAmountThree;
        this.logicalRelation = logicalRelation;
    }

}
