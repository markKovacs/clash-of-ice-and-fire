package com.coinf.util;

import com.coinf.entity.blueprint.HexNode;
import com.coinf.entity.enums.HexType;
import com.coinf.entity.instance.Game;
import com.coinf.entity.instance.Player;
import com.coinf.repository.GameRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private final static Logger LOG = Logger.getLogger(DataInitializer.class);

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("Start initializing data.");

        List<HexNode> hexNodes = new ArrayList<>();
        // ROW 1
        hexNodes.add(new HexNode(HexType.BLANK, 95, false, false, "", "", ""));
        hexNodes.add(new HexNode(HexType.WHITE_WALKER, 93, false, false, "land_of_ice", "Land of Ice", "This place is covered with ice."));
        hexNodes.add(new HexNode(HexType.BLANK, 91, false, false, "", "", ""));
        hexNodes.add(new HexNode(HexType.BLANK, 89, false, false, "", "", ""));
        hexNodes.add(new HexNode(HexType.STARK, 87, false, false, "winterfell", "Winterfell", "The capital of the North."));
        hexNodes.add(new HexNode(HexType.BLANK, 85, false, false, "", "", ""));
        hexNodes.add(new HexNode(HexType.BLANK, 83, false, false, "", "", ""));

        // ROW 2
        hexNodes.add(new HexNode(HexType.BLANK, 81, false, false, "", "", ""));




        Player player1 = new Player();

        Game game = new Game();

        //gameRepository.save(game);

        LOG.info("Finished initializing data.");
    }

}
