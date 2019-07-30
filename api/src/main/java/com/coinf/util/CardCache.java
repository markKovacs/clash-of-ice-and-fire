package com.coinf.util;

import com.coinf.entity.blueprint.EncounterCard;
import com.coinf.entity.blueprint.FactoryCard;
import com.coinf.entity.blueprint.ObjectiveCard;
import com.coinf.entity.blueprint.StructureBonus;
import com.coinf.entity.enums.StructureBonusType;
import com.coinf.repository.EncounterCardRepository;
import com.coinf.repository.FactoryCardRepository;
import com.coinf.repository.ObjectiveCardRepository;
import com.coinf.repository.StructureBonusRepository;
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
public class CardCache implements ApplicationListener<ApplicationReadyEvent> {

    private final static Logger LOG = Logger.getLogger(CardCache.class);

    @Autowired
    private EncounterCardRepository encounterCardRepository;
    @Autowired
    private FactoryCardRepository factoryCardRepository;
    @Autowired
    private ObjectiveCardRepository objectiveCardRepository;
    @Autowired
    private StructureBonusRepository structureBonusRepository;
    @Autowired
    private ObjectiveCardEnricher objEnricher;

    private Map<Integer, ObjectiveCard> objectiveCardsByCardNum;
    private Map<StructureBonusType, StructureBonus> structureBonusByType;
    private Map<Integer, FactoryCard> factoryCardsByCardNum;
    private Map<Integer, EncounterCard> encounterCardsByCardNum;

    public ObjectiveCard findObjCardByCardNumber(Integer num) {
        ObjectiveCard card = objectiveCardsByCardNum.get(num);
        if (card == null) {
            throw new IllegalArgumentException("No objective card found for card number " + num);
        }
        return card;
    }

    public StructureBonus findStructBonusByType(StructureBonusType type) {
        StructureBonus structureBonus = structureBonusByType.get(type);
        if (structureBonus == null) {
            throw new IllegalStateException("Could not find structure bonus of type " + type);
        }
        return structureBonus;
    }

    public FactoryCard findFactoryCardByCardNumber(Integer num) {
        FactoryCard card = factoryCardsByCardNum.get(num);
        if (card == null) {
            throw new IllegalArgumentException("No factory card found for card number " + num);
        }
        return card;
    }

    public EncounterCard findEncounterCardByCardNumber(Integer num) {
        EncounterCard card = encounterCardsByCardNum.get(num);
        if (card == null) {
            throw new IllegalArgumentException("No encounter card found for card number " + num);
        }
        return card;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOG.info("Initializing card data.");

        initObjectiveCardCache();
        initStructureBonusCache();
        initFactoryCardCache();
        initEncounterCardCache();

        LOG.info("Finished initializing card data.");
    }

    private void initEncounterCardCache() {
        List<EncounterCard> encounterCards = encounterCardRepository.findAll();
        if (CollectionUtils.isEmpty(encounterCards)) {
            throw new IllegalStateException("Encounter cards were not initialized.");
        }

        encounterCardsByCardNum = encounterCards.stream()
                .collect(Collectors.toMap(EncounterCard::getCardNumber, c -> c));
    }

    private void initFactoryCardCache() {
        List<FactoryCard> factoryCards = factoryCardRepository.findAll();
        if (CollectionUtils.isEmpty(factoryCards)) {
            throw new IllegalStateException("Factory cards were not initialized.");
        }

        factoryCardsByCardNum = factoryCards.stream()
                .collect(Collectors.toMap(FactoryCard::getCardNum, c -> c));
    }

    private void initStructureBonusCache() {
        List<StructureBonus> structureBonuses = structureBonusRepository.findAll();
        if (CollectionUtils.isEmpty(structureBonuses)) {
            throw new IllegalStateException("Objective cards were not initialized.");
        }

        structureBonusByType = structureBonuses.stream()
                .collect(Collectors.toMap(StructureBonus::getType, s -> s));
    }

    private void initObjectiveCardCache() {
        List<ObjectiveCard> objectiveCards = objectiveCardRepository.findAll();
        if (CollectionUtils.isEmpty(objectiveCards)) {
            throw new IllegalStateException("Objective cards were not initialized.");
        }
        for (ObjectiveCard card : objectiveCards) {
            if (card == null) {
                throw new IllegalStateException("An objective card was not initialized properly.");
            }
            objEnricher.enrichWithIsCompleted(card);
        }

        objectiveCardsByCardNum = objectiveCards.stream()
                .collect(Collectors.toMap(ObjectiveCard::getCardNumber, c -> c));
    }

}
