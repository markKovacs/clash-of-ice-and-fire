package com.coinf.util;

import com.coinf.entity.blueprint.Edge;
import com.coinf.entity.blueprint.HexNode;
import com.coinf.entity.enums.Direction;
import com.coinf.entity.enums.HexType;
import com.coinf.repository.EdgeRepository;
import com.coinf.repository.GameRepository;
import com.coinf.repository.HexNodeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(
        value="app.data.initialization",
        havingValue = "true",
        matchIfMissing = true)
public class DataInitializer implements ApplicationRunner {

    private final static Logger LOG = Logger.getLogger(DataInitializer.class);

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private EdgeRepository edgeRepository;
    @Autowired
    private HexNodeRepository hexNodeRepository;

    // TODO: make transactional work, but first read more about a proper config
    // @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("Start initializing data.");

        edgeRepository.deleteAll();
        hexNodeRepository.deleteAll();

        // ROW 1
        HexNode node_1_1 = HexNode.getBlankInstance(0);
        HexNode node_1_2 = HexNode.getInstance(HexType.WHITE_WALKER, 5, false, false, "Land of Always Winter", "Nothernmost place where always snows.");
        HexNode node_1_3 = HexNode.getBlankInstance(10);
        HexNode node_1_4 = HexNode.getBlankInstance(15);
        HexNode node_1_5 = HexNode.getInstance(HexType.STARK, 20, false, false, "Winterfell", "The capital of the North.");
        HexNode node_1_6 = HexNode.getBlankInstance(25);
        HexNode node_1_7 = HexNode.getBlankInstance(30);

        // ROW 2
        HexNode node_2_1 = HexNode.getBlankInstance(35);
        HexNode node_2_2 = HexNode.getInstance(HexType.MOUNTAIN, 40, false, false, "Mountain", "Just a mountain.");
        HexNode node_2_4 = HexNode.getInstance(HexType.VILLAGE, 50, true, false, "Village", "Just a village.");
        HexNode node_2_3 = HexNode.getInstance(HexType.FARM, 45, false, false, "Farm", "Just a farm.");
        HexNode node_2_5 = HexNode.getInstance(HexType.FOREST, 55, false, false, "Forest", "Just a forest.");
        HexNode node_2_6 = HexNode.getInstance(HexType.TUNDRA, 60, false, false, "Tundra", "Just a tundra.");
        HexNode node_2_7 = HexNode.getInstance(HexType.VILLAGE, 65, false, false, "Village", "Just a village.");
        HexNode node_2_8 = HexNode.getBlankInstance(70);

        // ROW 3
        HexNode node_3_1 = HexNode.getInstance(HexType.LAKE, 75, false, false, "Lake", "Just a lake.");
        HexNode node_3_2 = HexNode.getInstance(HexType.TUNDRA, 80, true, false, "Tundra", "Just a tundra.");
        HexNode node_3_3 = HexNode.getInstance(HexType.LAKE, 85, false, false, "Lake", "Just a lake.");
        HexNode node_3_4 = HexNode.getInstance(HexType.TUNDRA, 90, false, true, "Tundra", "Just a tundra.");
        HexNode node_3_5 = HexNode.getInstance(HexType.MOUNTAIN, 95, false, false, "Mountain", "Just a mountain.");
        HexNode node_3_6 = HexNode.getInstance(HexType.FARM, 100, false, false, "Farm", "Just a farm.");
        HexNode node_3_7 = HexNode.getInstance(HexType.FARM, 105, true, false, "Farm", "Just a farm.");

        // ROW 4
        HexNode node_4_1 = HexNode.getInstance(HexType.GREYJOY, 110, false, false, "Pike", "Capital of the Iron Islands.");
        HexNode node_4_2 = HexNode.getInstance(HexType.FOREST, 115, false, false, "Forest", "Just a forest.");
        HexNode node_4_3 = HexNode.getInstance(HexType.MOUNTAIN, 120, false, true, "Mountain", "Just a mountain.");
        HexNode node_4_4 = HexNode.getInstance(HexType.FOREST, 125, false, false, "Forest", "Just a forest.");
        HexNode node_4_5 = HexNode.getInstance(HexType.LAKE, 130, false, false, "Lake", "Just a lake.");
        HexNode node_4_6 = HexNode.getInstance(HexType.FOREST, 135, false, true, "Forest", "Just a forest.");
        HexNode node_4_7 = HexNode.getInstance(HexType.VILLAGE, 140, false, false, "Village", "Just a village.");
        HexNode node_4_8 = HexNode.getInstance(HexType.TARGARYEN, 145, false, false, "Dragonstone", "A barren island with a castle.");

        // ROW 5
        HexNode node_5_1 = HexNode.getInstance(HexType.FARM, 150, false, false, "Farm", "Just a farm.");
        HexNode node_5_2 = HexNode.getInstance(HexType.VILLAGE, 155, false, false, "Village", "Just a village.");
        HexNode node_5_3 = HexNode.getInstance(HexType.LAKE, 160, false, false, "Lake", "Just a lake.");
        HexNode node_5_4 = HexNode.getInstance(HexType.IRONTHRONE, 165, false, false, "Iron Throne", "The capital of Westeros.");
        HexNode node_5_5 = HexNode.getInstance(HexType.MOUNTAIN, 170, false, false, "Mountain", "Just a mountain.");
        HexNode node_5_6 = HexNode.getInstance(HexType.TUNDRA, 175, true, false, "Tundra", "Just a tundra.");
        HexNode node_5_7 = HexNode.getInstance(HexType.MOUNTAIN, 180, false, false, "Mountain", "Just a mountain.");

        // ROW 6
        HexNode node_6_1 = HexNode.getInstance(HexType.FOREST, 185, true, false, "Forest", "Just a forest.");
        HexNode node_6_2 = HexNode.getInstance(HexType.FOREST, 190, false, false, "Forest", "Just a forest.");
        HexNode node_6_3 = HexNode.getInstance(HexType.FARM, 195, false, true, "Farm", "Just a farm.");
        HexNode node_6_4 = HexNode.getInstance(HexType.TUNDRA, 200, false, false, "Tundra", "Just a tundra.");
        HexNode node_6_5 = HexNode.getInstance(HexType.LAKE, 205, false, false, "Lake", "Just a lake.");
        HexNode node_6_6 = HexNode.getInstance(HexType.VILLAGE, 210, false, true, "Village", "Just a village.");
        HexNode node_6_7 = HexNode.getInstance(HexType.LAKE, 215, false, false, "Lake", "Just a lake.");
        HexNode node_6_8 = HexNode.getBlankInstance(220);

        // ROW 7
        HexNode node_7_1 = HexNode.getInstance(HexType.MOUNTAIN, 225, false, false, "Mountain", "Just a mountain.");
        HexNode node_7_2 = HexNode.getInstance(HexType.VILLAGE, 230, true, false, "Village", "Just a village.");
        HexNode node_7_3 = HexNode.getInstance(HexType.VILLAGE, 235, true, false, "Village", "Just a village.");
        HexNode node_7_4 = HexNode.getInstance(HexType.TUNDRA, 240, false, true, "Tundra", "Just a tundra.");
        HexNode node_7_5 = HexNode.getInstance(HexType.FOREST, 245, false, false, "Forest", "Just a forest.");
        HexNode node_7_6 = HexNode.getInstance(HexType.MOUNTAIN, 250, true, false, "Mountain", "Just a mountain.");
        HexNode node_7_7 = HexNode.getInstance(HexType.TUNDRA, 255, false, false, "Tundra", "Just a tundra.");

        // ROW 8
        HexNode node_8_1 = HexNode.getInstance(HexType.LANNISTER, 260, false, false, "Casterly Rock", "The capital of Westerlands.");
        HexNode node_8_2 = HexNode.getInstance(HexType.TUNDRA, 265, false, false, "Tundra", "Just a tundra.");
        HexNode node_8_3 = HexNode.getInstance(HexType.LAKE, 270, false, false, "Lake", "Just a lake.");
        HexNode node_8_4 = HexNode.getInstance(HexType.FARM, 275, false, false, "Farm", "Just a farm.");
        HexNode node_8_5 = HexNode.getInstance(HexType.MOUNTAIN, 280, true, false, "Mountain", "Just a mountain.");
        HexNode node_8_6 = HexNode.getInstance(HexType.VILLAGE, 285, false, false, "Village", "Just a village.");
        HexNode node_8_7 = HexNode.getInstance(HexType.FARM, 290, false, false, "Farm", "Just a farm.");
        HexNode node_8_8 = HexNode.getInstance(HexType.BARATHEON, 295, false, false, "Storm's End", "The capital of Stormlands.");

        // ROW 9
        HexNode node_9_1 = HexNode.getBlankInstance(300);
        HexNode node_9_2 = HexNode.getBlankInstance(305);
        HexNode node_9_3 = HexNode.getInstance(HexType.TYRELL, 310, false, false, "Highgarden", "The capital of The Reach.");
        HexNode node_9_4 = HexNode.getInstance(HexType.VILLAGE, 315, false, false, "Village", "Just a village.");
        HexNode node_9_5 = HexNode.getBlankInstance(320);
        HexNode node_9_6 = HexNode.getBlankInstance(325);
        HexNode node_9_7 = HexNode.getBlankInstance(330);

        List<HexNode> hexNodes = new ArrayList<>(Arrays.asList(
                node_1_1, node_1_2, node_1_3, node_1_4, node_1_5, node_1_6, node_1_7,
                node_2_1, node_2_2, node_2_3, node_2_4, node_2_5, node_2_6, node_2_7, node_2_8,
                node_3_1, node_3_2, node_3_3, node_3_4, node_3_5, node_3_6, node_3_7,
                node_4_1, node_4_2, node_4_3, node_4_4, node_4_5, node_4_6, node_4_7, node_4_8,
                node_5_1, node_5_2, node_5_3, node_5_4, node_5_5, node_5_6, node_5_7,
                node_6_1, node_6_2, node_6_3, node_6_4, node_6_5, node_6_6, node_6_7, node_6_8,
                node_7_1, node_7_2, node_7_3, node_7_4, node_7_5, node_7_6, node_7_7,
                node_8_1, node_8_2, node_8_3, node_8_4, node_8_5, node_8_6, node_8_7, node_8_8,
                node_9_1, node_9_2, node_9_3, node_9_4, node_9_5, node_9_6, node_9_7
        ));

        List<Edge> edges = new ArrayList<>();

        // EDGES - ROW 1

        edges.add(new Edge(node_1_2, node_2_2, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_1_2, node_2_3, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_1_5, node_2_5, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_1_5, node_2_6, Direction.SOUTH_EAST, false));

        // EDGES - ROW 2
        edges.add(new Edge(node_2_2, node_1_2, Direction.EAST, false));
        edges.add(new Edge(node_2_2, node_1_2, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_2_2, node_1_2, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_2_3, node_2_4, Direction.EAST, false));
        edges.add(new Edge(node_2_3, node_3_3, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_2_3, node_3_2, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_2_3, node_2_2, Direction.WEST, false));
        edges.add(new Edge(node_2_4, node_2_5, Direction.EAST, true));
        edges.add(new Edge(node_2_4, node_3_4, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_2_4, node_3_3, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_2_4, node_2_3, Direction.WEST, false));
        edges.add(new Edge(node_2_5, node_2_6, Direction.EAST, false));
        edges.add(new Edge(node_2_5, node_3_5, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_2_5, node_3_4, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_2_5, node_2_4, Direction.WEST, true));
        edges.add(new Edge(node_2_6, node_2_7, Direction.EAST, true));
        edges.add(new Edge(node_2_6, node_3_6, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_2_6, node_3_5, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_2_6, node_2_5, Direction.WEST, false));
        edges.add(new Edge(node_2_7, node_3_7, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_2_7, node_3_6, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_2_7, node_2_6, Direction.WEST, true));

        // EDGES - ROW 3
        edges.add(new Edge(node_3_1, node_2_2, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_3_1, node_3_2, Direction.EAST, false));
        edges.add(new Edge(node_3_1, node_4_2, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_3_2, node_2_3, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_3_2, node_3_3, Direction.EAST, false));
        edges.add(new Edge(node_3_2, node_4_3, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_3_2, node_4_2, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_3_2, node_3_1, Direction.WEST, false));
        edges.add(new Edge(node_3_2, node_2_2, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_3_3, node_2_4, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_3_3, node_3_4, Direction.EAST, false));
        edges.add(new Edge(node_3_3, node_4_4, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_3_3, node_4_3, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_3_3, node_3_2, Direction.WEST, false));
        edges.add(new Edge(node_3_3, node_2_3, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_3_4, node_2_5, Direction.NORTH_EAST, true));
        edges.add(new Edge(node_3_4, node_3_5, Direction.EAST, true));
        edges.add(new Edge(node_3_4, node_4_5, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_3_4, node_4_4, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_3_4, node_3_3, Direction.WEST, false));
        edges.add(new Edge(node_3_4, node_2_4, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_3_5, node_2_6, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_3_5, node_3_6, Direction.EAST, true));
        edges.add(new Edge(node_3_5, node_4_6, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_3_5, node_4_5, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_3_5, node_3_4, Direction.WEST, true));
        edges.add(new Edge(node_3_5, node_2_5, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_3_6, node_2_7, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_3_6, node_3_7, Direction.EAST, false));
        edges.add(new Edge(node_3_6, node_4_7, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_3_6, node_4_6, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_3_6, node_3_5, Direction.WEST, true));
        edges.add(new Edge(node_3_6, node_2_6, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_3_7, node_4_7, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_3_7, node_3_6, Direction.WEST, false));
        edges.add(new Edge(node_3_7, node_2_7, Direction.NORTH_WEST, false));

        // EDGES - ROW 4
        edges.add(new Edge(node_4_1, node_3_1, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_4_1, node_4_2, Direction.EAST, false));
        edges.add(new Edge(node_4_1, node_5_1, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_4_2, node_3_2, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_4_2, node_4_3, Direction.EAST, true));
        edges.add(new Edge(node_4_2, node_5_2, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_4_2, node_5_1, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_4_2, node_3_1, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_4_3, node_3_3, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_4_3, node_4_4, Direction.EAST, false));
        edges.add(new Edge(node_4_3, node_5_3, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_4_3, node_5_2, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_4_3, node_4_2, Direction.WEST, true));
        edges.add(new Edge(node_4_3, node_3_2, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_4_4, node_3_4, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_4_4, node_4_5, Direction.EAST, false));
        edges.add(new Edge(node_4_4, node_5_4, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_4_4, node_5_3, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_4_4, node_4_3, Direction.WEST, false));
        edges.add(new Edge(node_4_4, node_3_3, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_4_5, node_3_5, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_4_5, node_4_6, Direction.EAST, false));
        edges.add(new Edge(node_4_5, node_5_5, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_4_5, node_5_4, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_4_5, node_4_4, Direction.WEST, false));
        edges.add(new Edge(node_4_5, node_3_4, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_4_6, node_3_6, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_4_6, node_4_7, Direction.EAST, true));
        edges.add(new Edge(node_4_6, node_5_6, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_4_6, node_5_5, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_4_6, node_4_5, Direction.WEST, false));
        edges.add(new Edge(node_4_6, node_3_5, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_4_7, node_3_7, Direction.NORTH_EAST, true));
        edges.add(new Edge(node_4_7, node_5_7, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_4_7, node_5_6, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_4_7, node_4_6, Direction.WEST, true));
        edges.add(new Edge(node_4_7, node_3_6, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_4_8, node_5_7, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_4_8, node_4_7, Direction.WEST, false));
        edges.add(new Edge(node_4_8, node_3_7, Direction.NORTH_WEST, true));

        // EDGES - ROW 5
        edges.add(new Edge(node_5_1, node_4_2, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_5_1, node_5_2, Direction.EAST, false));
        edges.add(new Edge(node_5_1, node_6_2, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_5_1, node_6_1, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_5_2, node_4_3, Direction.NORTH_EAST, true));
        edges.add(new Edge(node_5_2, node_5_3, Direction.EAST, false));
        edges.add(new Edge(node_5_2, node_6_3, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_5_2, node_6_2, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_5_2, node_5_1, Direction.WEST, false));
        edges.add(new Edge(node_5_2, node_4_2, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_5_3, node_4_4, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_5_3, node_5_4, Direction.EAST, false));
        edges.add(new Edge(node_5_3, node_6_4, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_5_3, node_6_3, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_5_3, node_5_2, Direction.WEST, false));
        edges.add(new Edge(node_5_3, node_4_3, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_5_4, node_4_5, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_5_4, node_5_5, Direction.EAST, false));
        edges.add(new Edge(node_5_4, node_6_5, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_5_4, node_6_4, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_5_4, node_5_3, Direction.WEST, false));
        edges.add(new Edge(node_5_4, node_4_4, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_5_5, node_4_6, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_5_5, node_5_6, Direction.EAST, true));
        edges.add(new Edge(node_5_5, node_6_6, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_5_5, node_6_5, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_5_5, node_5_4, Direction.WEST, false));
        edges.add(new Edge(node_5_5, node_4_5, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_5_6, node_4_7, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_5_6, node_5_7, Direction.EAST, false));
        edges.add(new Edge(node_5_6, node_6_7, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_5_6, node_6_6, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_5_6, node_5_5, Direction.WEST, true));
        edges.add(new Edge(node_5_6, node_4_6, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_5_7, node_6_7, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_5_7, node_5_6, Direction.WEST, false));
        edges.add(new Edge(node_5_7, node_4_7, Direction.NORTH_WEST, false));

        // EDGES - ROW 6
        edges.add(new Edge(node_6_1, node_5_1, Direction.NORTH_EAST, true));
        edges.add(new Edge(node_6_1, node_6_2, Direction.EAST, false));
        edges.add(new Edge(node_6_1, node_7_1, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_6_2, node_5_2, Direction.NORTH_EAST, true));
        edges.add(new Edge(node_6_2, node_6_3, Direction.EAST, false));
        edges.add(new Edge(node_6_2, node_7_2, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_6_2, node_7_1, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_6_2, node_6_1, Direction.WEST, false));
        edges.add(new Edge(node_6_2, node_5_1, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_6_3, node_5_3, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_6_3, node_6_4, Direction.EAST, false));
        edges.add(new Edge(node_6_3, node_7_3, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_6_3, node_7_2, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_6_3, node_6_2, Direction.WEST, false));
        edges.add(new Edge(node_6_3, node_5_2, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_6_4, node_5_4, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_6_4, node_6_5, Direction.EAST, false));
        edges.add(new Edge(node_6_4, node_7_4, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_6_4, node_7_3, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_6_4, node_6_3, Direction.WEST, false));
        edges.add(new Edge(node_6_4, node_5_3, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_6_5, node_5_5, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_6_5, node_6_6, Direction.EAST, false));
        edges.add(new Edge(node_6_5, node_7_5, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_6_5, node_7_4, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_6_5, node_6_4, Direction.WEST, false));
        edges.add(new Edge(node_6_5, node_5_4, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_6_6, node_5_6, Direction.NORTH_EAST, true));
        edges.add(new Edge(node_6_6, node_6_7, Direction.EAST, false));
        edges.add(new Edge(node_6_6, node_7_6, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_6_6, node_7_5, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_6_6, node_6_5, Direction.WEST, false));
        edges.add(new Edge(node_6_6, node_5_5, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_6_7, node_5_7, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_6_7, node_7_7, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_6_7, node_7_6, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_6_7, node_6_6, Direction.WEST, false));
        edges.add(new Edge(node_6_7, node_5_6, Direction.NORTH_WEST, false));

        // EDGES - ROW 7
        edges.add(new Edge(node_7_1, node_6_2, Direction.NORTH_EAST, true));
        edges.add(new Edge(node_7_1, node_6_2, Direction.EAST, false));
        edges.add(new Edge(node_7_1, node_6_2, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_7_1, node_6_2, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_7_2, node_6_3, Direction.NORTH_EAST, true));
        edges.add(new Edge(node_7_2, node_7_3, Direction.EAST, true));
        edges.add(new Edge(node_7_2, node_8_3, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_7_2, node_8_2, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_7_2, node_7_1, Direction.WEST, false));
        edges.add(new Edge(node_7_2, node_6_2, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_7_3, node_6_4, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_7_3, node_7_4, Direction.EAST, false));
        edges.add(new Edge(node_7_3, node_8_4, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_7_3, node_8_3, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_7_3, node_7_2, Direction.WEST, true));
        edges.add(new Edge(node_7_3, node_6_3, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_7_4, node_6_5, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_7_4, node_7_5, Direction.EAST, false));
        edges.add(new Edge(node_7_4, node_8_5, Direction.SOUTH_EAST, true));
        edges.add(new Edge(node_7_4, node_8_4, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_7_4, node_7_3, Direction.WEST, false));
        edges.add(new Edge(node_7_4, node_6_4, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_7_5, node_6_6, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_7_5, node_7_6, Direction.EAST, false));
        edges.add(new Edge(node_7_5, node_8_6, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_7_5, node_8_5, Direction.SOUTH_WEST, true));
        edges.add(new Edge(node_7_5, node_7_4, Direction.WEST, false));
        edges.add(new Edge(node_7_5, node_6_5, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_7_6, node_6_7, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_7_6, node_7_7, Direction.EAST, false));
        edges.add(new Edge(node_7_6, node_8_7, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_7_6, node_8_6, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_7_6, node_7_5, Direction.WEST, false));
        edges.add(new Edge(node_7_6, node_6_6, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_7_7, node_8_7, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_7_7, node_7_6, Direction.WEST, false));
        edges.add(new Edge(node_7_7, node_6_7, Direction.NORTH_WEST, false));

        // EDGES - ROW 8
        edges.add(new Edge(node_8_1, node_7_1, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_8_1, node_8_2, Direction.EAST, false));
        edges.add(new Edge(node_8_2, node_7_2, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_8_2, node_8_3, Direction.EAST, false));
        edges.add(new Edge(node_8_2, node_7_1, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_8_3, node_7_3, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_8_3, node_8_4, Direction.EAST, false));
        edges.add(new Edge(node_8_3, node_8_2, Direction.WEST, false));
        edges.add(new Edge(node_8_3, node_7_2, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_8_4, node_7_4, Direction.NORTH_EAST, true));
        edges.add(new Edge(node_8_4, node_8_5, Direction.EAST, false));
        edges.add(new Edge(node_8_4, node_9_4, Direction.SOUTH_EAST, false));
        edges.add(new Edge(node_8_4, node_8_3, Direction.WEST, false));
        edges.add(new Edge(node_8_4, node_7_3, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_8_5, node_7_5, Direction.NORTH_EAST, true));
        edges.add(new Edge(node_8_5, node_8_6, Direction.EAST, true));
        edges.add(new Edge(node_8_5, node_9_4, Direction.SOUTH_WEST, false));
        edges.add(new Edge(node_8_5, node_8_4, Direction.WEST, false));
        edges.add(new Edge(node_8_5, node_7_4, Direction.NORTH_WEST, true));
        edges.add(new Edge(node_8_6, node_7_6, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_8_6, node_8_7, Direction.EAST, false));
        edges.add(new Edge(node_8_6, node_8_5, Direction.WEST, true));
        edges.add(new Edge(node_8_6, node_7_5, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_8_7, node_7_7, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_8_7, node_8_6, Direction.WEST, false));
        edges.add(new Edge(node_8_7, node_7_6, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_8_8, node_8_7, Direction.WEST, false));
        edges.add(new Edge(node_8_8, node_7_7, Direction.NORTH_WEST, false));

        // EDGES - ROW 9
        edges.add(new Edge(node_9_3, node_8_4, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_9_3, node_9_4, Direction.EAST, false));
        edges.add(new Edge(node_9_3, node_8_3, Direction.NORTH_WEST, false));
        edges.add(new Edge(node_9_4, node_8_5, Direction.NORTH_EAST, false));
        edges.add(new Edge(node_9_4, node_8_4, Direction.NORTH_WEST, false));

        hexNodeRepository.saveAll(hexNodes);
        edgeRepository.saveAll(edges);

        LOG.info("Finished initializing data.");
    }

}
