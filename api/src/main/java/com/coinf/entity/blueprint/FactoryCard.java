package com.coinf.entity.blueprint;

import com.coinf.entity.enums.GainType;
import com.coinf.entity.enums.LogicalRelation;
import com.coinf.entity.enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class FactoryCard {

    @Id
    private Integer cardNum;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentTypeOne;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentTypeTwo;

    @Enumerated(EnumType.STRING)
    private GainType gainTypeOne;

    @Enumerated(EnumType.STRING)
    private GainType gainTypeTwo;

    @Enumerated(EnumType.STRING)
    private GainType gainTypeThree;

    @Enumerated(EnumType.STRING)
    private LogicalRelation gainRelation;

    public FactoryCard(int cardNum,
                       PaymentType paymentTypeOne, PaymentType paymentTypeTwo,
                       GainType gainTypeOne, GainType gainTypeTwo, GainType gainTypeThree,
                       LogicalRelation gainRelation) {
        this.cardNum = cardNum;
        this.paymentTypeOne = paymentTypeOne;
        this.paymentTypeTwo = paymentTypeTwo;
        this.gainTypeOne = gainTypeOne;
        this.gainTypeTwo = gainTypeTwo;
        this.gainTypeThree = gainTypeThree;
        this.gainRelation = gainRelation;
    }

}
