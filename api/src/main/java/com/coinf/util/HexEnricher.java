package com.coinf.util;

import com.coinf.cache.BoardCache;
import com.coinf.entity.instance.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HexEnricher {

    @Autowired
    private BoardCache boardCache;

    public void enrichWithHexNode(Hex hex) {
        hex.setHexNode(boardCache.findHexNode(hex.getHexNodeId()));
    }

}
