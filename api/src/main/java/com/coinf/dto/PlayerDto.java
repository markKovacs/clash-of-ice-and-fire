package com.coinf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PlayerDto {

    private int coins;
    private String user;
    private PlayerMatDto playerMat;
    private FactionMatDto factionMat;
    private List<Integer> combatCards;
    private List<ObjectiveCardDto> objectiveCards;

}
