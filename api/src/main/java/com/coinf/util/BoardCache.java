package com.coinf.util;

import com.coinf.entity.blueprint.Edge;
import com.coinf.entity.blueprint.HexNode;
import com.coinf.entity.enums.HexType;
import com.coinf.repository.EdgeRepository;
import com.coinf.repository.HexNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@DependsOn("dataInitializer")
public class BoardCache implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private EdgeRepository edgeRepository;

    @Autowired
    private HexNodeRepository hexNodeRepository;

    private List<Edge> edges;

    private List<HexNode> hexNodes;

    private Map<Long, HexNode> hexNodeMap;

    public HexNode findHexNode(Long hexNodeId) {
        return hexNodeMap.get(hexNodeId);
    }

    public HexNode findUniqueHexNodeByType(HexType hexType) {
        List<HexNode> found = getNodesByType(hexType);
        if (found.size() > 1) {
            throw new IllegalArgumentException("HexType " + hexType + " is invalid because corresponds to multiple nodes.");
        }
        return found.get(0);
    }

    public List<HexNode> findHexNodesByType(HexType hexType) {
        return getNodesByType(hexType);
    }

    public List<HexNode> getAllTunnels() {
        return hexNodes.stream()
                .filter(HexNode::isTunnel)
                .collect(Collectors.toList());
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        edges = edgeRepository.findAll();
        hexNodes = hexNodeRepository.findAll();
        hexNodeMap = hexNodes.stream().collect(Collectors.toMap(HexNode::getId, hex -> hex));

        for (HexNode hexNode : hexNodes) {
            hexNode.addEdges(edges.stream()
                    .filter(e -> hexNode.equals(e.getSource()))
                    .collect(Collectors.toList()));
        }
    }

    private List<HexNode> getNodesByType(HexType hexType) {
        return hexNodes.stream()
                .filter(node -> node.getHexType().equals(hexType))
                .collect(Collectors.toList());
    }

}
