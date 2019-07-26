package com.coinf.dto;

import com.coinf.entity.blueprint.HexNode;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HexNodeDepth {

    private HexNode hexNode;
    private int depth;

}
