package com.coinf.util;

import com.coinf.entity.blueprint.EncounterCard;
import com.coinf.entity.blueprint.EncounterOption;
import com.coinf.entity.enums.GainType;
import com.coinf.entity.enums.PaymentType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EncounterCardEnricher {

    private Map<Integer, List<EncounterOption>> optionsByCardNum;

    @PostConstruct
    public void initialize() {
        optionsByCardNum = new HashMap<>();
        optionsByCardNum.put(1, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.FOOD, 4, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.BUILD, 1, null, 0)
        ));
        optionsByCardNum.put(2, Arrays.asList(
                new EncounterOption(null, 0, GainType.COMBAT_CARD, 1, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.WOOD, 4, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.ENLIST, 1, null, 0)
        ));
        optionsByCardNum.put(3, Arrays.asList(
                new EncounterOption(null, 0, GainType.FOOD, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.WORKER, 1, GainType.FOOD, 3),
                new EncounterOption(PaymentType.POPULARITY, 3, GainType.DEPLOY, 1, null, 0)
        ));
        optionsByCardNum.put(4, Arrays.asList(
                new EncounterOption(null, 0, GainType.METAL, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 3, GainType.ENLIST, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.METAL, 4, null, 0)
        ));
        optionsByCardNum.put(5, Arrays.asList(
                new EncounterOption(null, 0, GainType.FOOD, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 4, GainType.DEPLOY, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.FOOD, 2, GainType.METAL, 2)
        ));
        optionsByCardNum.put(6, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 3, GainType.ENLIST, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.FOOD, 4, null, 0)
        ));
        optionsByCardNum.put(7, Arrays.asList(
                new EncounterOption(null, 0, GainType.OIL, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.UPGRADE, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.OIL, 4, null, 0)
        ));
        optionsByCardNum.put(8, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.OIL, 4, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.ENLIST, 1, null, 0)
        ));
        optionsByCardNum.put(9, Arrays.asList(
                new EncounterOption(null, 0, GainType.OIL, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 3, GainType.BUILD, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.OIL, 4, null, 0)
        ));
        optionsByCardNum.put(10, Arrays.asList(
                new EncounterOption(null, 0, GainType.OIL, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 4, GainType.DEPLOY, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.COIN, 2, GainType.ANY_RESOURCE, 2)
        ));
        optionsByCardNum.put(11, Arrays.asList(
                new EncounterOption(null, 0, GainType.FOOD, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.WORKER, 1, GainType.FOOD, 3),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.BUILD, 1, null, 0)
        ));
        optionsByCardNum.put(12, Arrays.asList(
                new EncounterOption(null, 0, GainType.WOOD, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 3, GainType.BUILD, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.WORKER, 1, GainType.WOOD, 3)
        ));
        optionsByCardNum.put(13, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.POWER, 2, GainType.POPULARITY, 2),
                new EncounterOption(PaymentType.POPULARITY, 3, GainType.ANY_RESOURCE, 5, null, 0)
        ));
        optionsByCardNum.put(14, Arrays.asList(
                new EncounterOption(null, 0, GainType.FOOD, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.ANY_RESOURCE, 3, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.WORKER, 1, GainType.METAL, 3)
        ));
        optionsByCardNum.put(15, Arrays.asList(
                new EncounterOption(null, 0, GainType.POPULARITY, 2, null, 0),
                new EncounterOption(PaymentType.COIN, 2, GainType.ANY_RESOURCE, 3, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 3, GainType.ANY_RESOURCE, 5, null, 0)
        ));
        optionsByCardNum.put(16, Arrays.asList(
                new EncounterOption(null, 0, GainType.FOOD, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.WORKER, 1, GainType.ANY_RESOURCE, 2),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.ENLIST, 1, null, 0)
        ));
        optionsByCardNum.put(17, Arrays.asList(
                new EncounterOption(null, 0, GainType.COMBAT_CARD, 1, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.POWER, 2, GainType.COMBAT_CARD, 2),
                new EncounterOption(PaymentType.POPULARITY, 3, GainType.DEPLOY, 1, null, 0)
        ));
        optionsByCardNum.put(18, Arrays.asList(
                new EncounterOption(null, 0, GainType.FOOD, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.ANY_RESOURCE, 3, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.WORKER, 1, GainType.WOOD, 3)
        ));
        optionsByCardNum.put(19, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.FOOD, 4, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.COMBAT_CARD, 2, GainType.POWER, 3)
        ));
        optionsByCardNum.put(20, Arrays.asList(
                new EncounterOption(null, 0, GainType.COMBAT_CARD, 1, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.POPULARITY, 3, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.WOOD, 4, null, 0)
        ));
        optionsByCardNum.put(21, Arrays.asList(
                new EncounterOption(null, 0, GainType.POPULARITY, 2, null, 0),
                new EncounterOption(PaymentType.COIN, 4, GainType.DEPLOY, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.COMBAT_CARD, 2, GainType.POWER, 3)
        ));
        optionsByCardNum.put(22, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.METAL, 4, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.BUILD, 1, null, 0)
        ));
        optionsByCardNum.put(23, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 3, GainType.ENLIST, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.WORKER, 1, GainType.FOOD, 3)
        ));
        optionsByCardNum.put(24, Arrays.asList(
                new EncounterOption(null, 0, GainType.COMBAT_CARD, 1, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.ANY_RESOURCE, 3, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 3, GainType.DEPLOY, 1, null, 0)
        ));
        optionsByCardNum.put(25, Arrays.asList(
                new EncounterOption(null, 0, GainType.POWER, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.POWER, 4, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.UPGRADE, 1, GainType.ANY_RESOURCE, 2)
        ));
        optionsByCardNum.put(26, Arrays.asList(
                new EncounterOption(null, 0, GainType.POWER, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.POPULARITY, 3, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.BUILD, 1, null, 0)
        ));
        optionsByCardNum.put(27, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.METAL, 4, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 3, GainType.DEPLOY, 1, null, 0)
        ));
        optionsByCardNum.put(28, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.WORKER, 1, GainType.ANY_RESOURCE, 2),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.BUILD, 1, null, 0)
        ));
        optionsByCardNum.put(29, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 3, GainType.UPGRADE, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.OIL, 3, GainType.FOOD, 1)
        ));
        optionsByCardNum.put(30, Arrays.asList(
                new EncounterOption(null, 0, GainType.POWER, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.POWER, 4, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.BUILD, 1, null, 0)
        ));
        // 31 MISSING INTENTIONALLY
        optionsByCardNum.put(32, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.POWER, 2, GainType.COMBAT_CARD, 2),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.UPGRADE, 1, GainType.ANY_RESOURCE, 2)
        ));
        optionsByCardNum.put(33, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.POWER, 2, GainType.COMBAT_CARD, 2),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.UPGRADE, 1, GainType.FOOD, 2)
        ));
        optionsByCardNum.put(34, Arrays.asList(
                new EncounterOption(null, 0, GainType.COIN, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 2, GainType.METAL, 4, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.ENLIST, 1, null, 0)
        ));
        optionsByCardNum.put(35, Arrays.asList(
                new EncounterOption(null, 0, GainType.POPULARITY, 2, null, 0),
                new EncounterOption(PaymentType.COIN, 2, GainType.WOOD, 4, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.WORKER, 1, GainType.OIL, 3)
        ));
        optionsByCardNum.put(36, Arrays.asList(
                new EncounterOption(null, 0, GainType.OIL, 2, GainType.POPULARITY, 1),
                new EncounterOption(PaymentType.COIN, 4, GainType.DEPLOY, 1, null, 0),
                new EncounterOption(PaymentType.POPULARITY, 2, GainType.WORKER, 1, GainType.OIL, 3)
        ));
    }

    public void enrichWithOptions(EncounterCard card) {
        if (optionsByCardNum.isEmpty()) {
            throw new IllegalStateException("Encounter options logic temporal cache was cleared too soon.");
        }
        List<EncounterOption> encounterOptions = optionsByCardNum.get(card.getCardNumber());
        if (encounterOptions == null) {
            throw new IllegalStateException("Encounter card with card number " + card.getCardNumber() + " not found in cache");
        }
        card.mergeOptionsLogic(encounterOptions);
    }

    public void clearTemporalCache() {
        // TODO: MAKE SURE TO USE ONCE DONE
        // USE ONLY AFTER CARD CACHE WAS CREATED AND ALL OPTIONS LOGIC DATA IS COPIED FROM HERE
        this.optionsByCardNum.clear();
    }

}
