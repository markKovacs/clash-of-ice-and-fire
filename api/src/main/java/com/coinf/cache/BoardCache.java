package com.coinf.cache;

import com.coinf.entity.blueprint.Edge;
import com.coinf.entity.blueprint.HexNode;
import com.coinf.repository.EdgeRepository;
import com.coinf.repository.HexNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BoardCache {

    @Autowired
    private EdgeRepository edgeRepository;

    @Autowired
    private HexNodeRepository hexNodeRepository;

    private List<Edge> edges;

    private List<HexNode> hexNodes;

    private Map<Long, HexNode> hexNodeMap;

    @PostConstruct
    public void init() {
        edges = edgeRepository.findAll();
        hexNodes = hexNodeRepository.findAll();
        hexNodeMap = hexNodes.stream().collect(Collectors.toMap(HexNode::getId, hex -> hex));

        for (HexNode hexNode : hexNodes) {
            hexNode.addEdges(edges.stream()
                    .filter(e -> hexNode.equals(e.getSource()))
                    .collect(Collectors.toList()));
        }
    }

    public HexNode findHexNode(Long hexNodeId) {
        return hexNodeMap.get(hexNodeId);
    }
}
