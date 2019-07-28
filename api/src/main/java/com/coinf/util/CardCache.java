package com.coinf.util;

import com.coinf.entity.blueprint.ObjectiveCard;
import com.coinf.entity.blueprint.StructureBonus;
import com.coinf.entity.enums.StructureBonusType;
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

    // TODO: Encounter and factory cards need to be inited and cached,
    //  provide some find/get methods as well (from a map)

    private final static Logger LOG = Logger.getLogger(CardCache.class);

    @Autowired
    private ObjectiveCardRepository objectiveCardRepository;
    @Autowired
    private StructureBonusRepository structureBonusRepository;
    @Autowired
    private ObjectiveCardEnricher objEnricher;

    private Map<Integer, ObjectiveCard> objectiveCardsByCardNum;
    private Map<StructureBonusType, StructureBonus> structureBonusByType;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOG.info("Initializing card data.");

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

        List<StructureBonus> structureBonuses = structureBonusRepository.findAll();
        if (CollectionUtils.isEmpty(structureBonuses)) {
            throw new IllegalStateException("Objective cards were not initialized.");
        }

        structureBonusByType = structureBonuses.stream()
                .collect(Collectors.toMap(StructureBonus::getType, s -> s));

        LOG.info("Finished initializing card data.");
    }

    public ObjectiveCard findByCardNumber(Integer num) {
        ObjectiveCard card = objectiveCardsByCardNum.get(num);
        if (card == null) {
            throw new IllegalArgumentException("No objective card found for card number " + num);
        }
        return card;
    }

    public StructureBonus findByType(StructureBonusType type) {
        StructureBonus structureBonus = structureBonusByType.get(type);
        if (structureBonus == null) {
            throw new IllegalStateException("Could not find structure bonus of type " + type);
        }
        return structureBonus;
    }

}
