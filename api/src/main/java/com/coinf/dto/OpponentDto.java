package com.coinf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OpponentDto {

    private String user;
    private PlayerMatDto playerMat;
    private FactionMatDto factionMat;
    private Integer coins;

}
