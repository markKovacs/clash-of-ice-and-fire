package com.coinf.service;

import com.coinf.entity.blueprint.HexNode;
import com.coinf.entity.enums.HexType;
import com.coinf.entity.instance.Game;
import com.coinf.entity.instance.Hex;
import com.coinf.entity.instance.Player;
import com.coinf.repository.HexRepository;
import com.coinf.util.BoardCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HexService {

    @Autowired
    private BoardCache boardCache;
    @Autowired
    private HexRepository hexRepository;

    public List<HexNode> getControlledByType(Game game, Player player, HexType hexType) {
        return game.getHexes()
                .stream()
                .filter(hex -> hex.isControlledBy(player))
                .map(hex -> boardCache.getHexNode(hex.getHexNodeId()))
                .filter(hexNode -> hexNode.ofType(hexType))
                .collect(Collectors.toList());
    }

    public List<HexNode> getAllControlledTunnels(Game game, Player player) {
        return game.getHexes()
                .stream()
                .filter(hex -> hex.isControlledBy(player))
                .map(hex -> boardCache.getHexNode(hex.getHexNodeId()))
                .filter(HexNode::isTunnel)
                .collect(Collectors.toList());
    }

    public List<Hex> getAllControlledHexes(Game game, Player player) {
        return game.getHexes()
                .stream()
                .filter(hex -> hex.isControlledBy(player))
                .collect(Collectors.toList());
    }

    public Hex getHexForGameAndNodeId(Game game, Long id) {
        return hexRepository.findByGameAndHexNodeId(game, id);
    }

    public List<Hex> getHexesForGameAndNodeIds(Game game, List<Long> ids) {
        return hexRepository.batchGetByGameAndHexNodeId(game, ids);
    }

}
