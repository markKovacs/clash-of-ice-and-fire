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
    private ObjectiveCardEnricher enricher;

    private List<ObjectiveCard> objectiveCards;
    private List<StructureBonus> structureBonuses;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOG.info("Initializing card data.");

        objectiveCards = objectiveCardRepository.findAll();
        if (CollectionUtils.isEmpty(objectiveCards)) {
            throw new IllegalStateException("Objective cards were not initialized.");
        }
        for (ObjectiveCard card : objectiveCards) {
            if (card == null) {
                throw new IllegalStateException("An objective card was not initialized properly.");
            }
            enricher.enrichWithIsCompleted(card);
        }

        structureBonuses = structureBonusRepository.findAll();
        if (CollectionUtils.isEmpty(structureBonuses)) {
            throw new IllegalStateException("Objective cards were not initialized.");
        }

        LOG.info("Finished initializing card data.");
    }

    public ObjectiveCard findByCardNumber(Integer num) {
        // TODO: Could use an internal map, grouped by card number
        ObjectiveCard card = objectiveCards
                .stream()
                .filter(c -> c.getCardNumber().equals(num))
                .findFirst()
                .orElse(null);

        if (card == null) {
            throw new IllegalArgumentException("No objective card found for card number " + num);
        }
        return card;
    }

    public StructureBonus findByType(StructureBonusType type) {
        // TODO: Could use an internal map, grouped by type
        return structureBonuses.stream()
                .filter(bonus -> bonus.ofType(type))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not find structure bonus of type " + type));
    }

}
