package com.coinf.entity.blueprint;

import com.coinf.entity.enums.GainType;
import com.coinf.entity.enums.LogicalRelation;
import com.coinf.entity.enums.PaymentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@DiscriminatorValue("FACTORY")
@Entity
public class FactoryTopRowAction extends TopRowActionParent {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
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

    // TODO stopped here, TopRowActionParent should be abstract and create another regular class from it,
    //  because playerMatSection conflicts in this and TopRowActionParent...

}
