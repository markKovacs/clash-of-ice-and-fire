package com.coinf.util;

import com.coinf.entity.blueprint.FactionMatLayout;
import com.coinf.entity.blueprint.PlayerMatLayout;
import com.coinf.entity.enums.Faction;
import com.coinf.entity.enums.PlayerMatLayoutType;
import com.coinf.repository.FactionMatLayoutRepository;
import com.coinf.repository.PlayerMatLayoutRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@DependsOn("dataInitializer")
public class MatLayoutCache implements ApplicationListener<ApplicationReadyEvent> {

    private final static Logger LOG = Logger.getLogger(MatLayoutCache.class);

    @Autowired
    private PlayerMatLayoutRepository playerMatLayoutRepository;
    @Autowired
    private FactionMatLayoutRepository factionMatLayoutRepository;

    private Map<PlayerMatLayoutType, PlayerMatLayout> playerMatLayoutsByType;
    private Map<Faction, FactionMatLayout> factionMatLayoutsByFaction;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOG.info("Initializing mat layout data.");

        List<PlayerMatLayout> playerMatLayouts = playerMatLayoutRepository.findAll();
        List<FactionMatLayout> factionMatLayouts = factionMatLayoutRepository.findAll();

        playerMatLayoutsByType = new EnumMap<>(PlayerMatLayoutType.class);
        for (PlayerMatLayout playerMatLayout : playerMatLayouts) {
            playerMatLayoutsByType.put(playerMatLayout.getPlayerMatLayoutType(), playerMatLayout);
        }

        factionMatLayoutsByFaction = new EnumMap<>(Faction.class);
        for (FactionMatLayout factionMatLayout : factionMatLayouts) {
            factionMatLayoutsByFaction.put(factionMatLayout.getFaction(), factionMatLayout);
        }

        if (CollectionUtils.isEmpty(playerMatLayouts) || CollectionUtils.isEmpty(factionMatLayouts)) {
            throw new IllegalStateException("Player/faction mats were not initialized.");
        }
        LOG.info("Finished initializing mat layout data.");
    }

    public PlayerMatLayout findByType(PlayerMatLayoutType type) {
        return playerMatLayoutsByType.get(type);
    }

    public FactionMatLayout findByType(Faction faction) {
        return factionMatLayoutsByFaction.get(faction);
    }

}
