package com.coinf.dto;

import com.coinf.entity.enums.BottomRowActionType;
import com.coinf.entity.enums.TopRowActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PlayerMatSectionDto {

    private int position;

    // BOTTOM ROW SPECIFIC
    private BottomRowActionType bottomType;
    private int bottomFixedPayment;
    private int bottomReducablePayment;
    private int bottomGainableCoins;

    // TOP ROW SPECIFIC
    private TopRowActionType topType;

}
