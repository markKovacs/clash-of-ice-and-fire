package com.coinf.dto;

import com.coinf.entity.enums.GainType;
import com.coinf.entity.enums.LogicalRelation;
import com.coinf.entity.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FactoryCardDto {

    private Integer cardNum;
    private PaymentType paymentTypeOne;
    private PaymentType paymentTypeTwo;
    private GainType gainTypeOne;
    private GainType gainTypeTwo;
    private GainType gainTypeThree;
    private LogicalRelation gainRelation;

}
