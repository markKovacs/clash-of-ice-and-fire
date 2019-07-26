package com.coinf.util;

import com.coinf.entity.blueprint.Edge;
import com.coinf.entity.blueprint.HexNode;
import com.coinf.entity.enums.HexType;
import com.coinf.repository.EdgeRepository;
import com.coinf.repository.HexNodeRepository;
import org.apache.log4j.Logger;
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

    private final static Logger LOG = Logger.getLogger(BoardCache.class);

    @Autowired
    private EdgeRepository edgeRepository;
    @Autowired
    private HexNodeRepository hexNodeRepository;

    private List<HexNode> hexNodes;
    private Map<Long, HexNode> hexNodeMap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOG.info("Initializing HexNode and Edge data.");

        List<Edge> edges = edgeRepository.findAll();
        hexNodes = hexNodeRepository.findAll();

        if (CollectionUtils.isEmpty(edges) || CollectionUtils.isEmpty(hexNodes)) {
            throw new IllegalStateException("Board data was not initialized.");
        }

        hexNodeMap = hexNodes.stream().collect(Collectors.toMap(HexNode::getId, hex -> hex));

        for (HexNode hexNode : hexNodes) {
            hexNode.addEdges(edges.stream()
                    .filter(e -> hexNode.equals(e.getSource()))
                    .collect(Collectors.toList()));
        }
        LOG.info("Finished initializing HexNode and Edge data.");
    }

    public List<HexNode> getAll() {
        return hexNodes;
    }

    public HexNode getHexNode(Long hexNodeId) {
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
        // TODO: Could use an internal map, grouped by HexType.tostring (including tunnel)
        return hexNodes.stream()
                .filter(HexNode::isTunnel)
                .collect(Collectors.toList());
    }

    private List<HexNode> getNodesByType(HexType hexType) {
        // TODO: Could use an internal map, grouped by HexType.tostring (including tunnel)
        return hexNodes.stream()
                .filter(node -> node.getHexType().equals(hexType))
                .collect(Collectors.toList());
    }

}
