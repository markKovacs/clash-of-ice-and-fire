package com.coinf.service;

import com.coinf.entity.blueprint.FactionMatLayout;
import com.coinf.entity.blueprint.HexNode;
import com.coinf.entity.blueprint.PlayerMatLayout;
import com.coinf.entity.enums.Faction;
import com.coinf.entity.enums.PlayerMatLayoutType;
import com.coinf.entity.enums.StructureBonusType;
import com.coinf.entity.enums.TriumphTrackSectionType;
import com.coinf.entity.instance.*;
import com.coinf.util.BoardCache;
import com.coinf.util.MatLayoutCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class GameInitializer {

    @Autowired
    private MatLayoutCache matLayoutCache;
    @Autowired
    private BoardCache boardCache;

    public Game init(List<Account> accounts) {

        // CREATE  COMBAT CARDS
        List<Integer> combatCards = generateCombatCards();

        // CREATE PLAYERS
        List<Integer> objectiveCards = generateObjectiveCardNumbers();
        List<Faction> factions = Arrays.asList(Faction.values());
        Collections.shuffle(factions);
        List<PlayerMatLayoutType> playerMatTypes = Arrays.asList(PlayerMatLayoutType.values());
        Collections.shuffle(playerMatTypes);

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);

            // CREATE PLAYER MAT
            PlayerMatLayout playerMatLayout = matLayoutCache.findByType(playerMatTypes.get(i));
            PlayerMat playerMat = PlayerMat.of(playerMatTypes.get(i));

            // CREATE FACTION MAT
            FactionMatLayout factionMatLayout = matLayoutCache.findByType(factions.get(i));
            FactionMat factionMat = FactionMat.of(factions.get(i));

            // DRAW COMBAT CARDS
            List<Integer> playersCombatCards = combatCards.subList(0, factionMatLayout.getCombatCards());
            combatCards = combatCards.subList(factionMatLayout.getCombatCards(), combatCards.size());

            // DRAW OBJECTIVE CARDS
            List<Integer> playersObjectiveCards = objectiveCards.subList(0, playerMatLayout.getObjectives());
            objectiveCards = objectiveCards.subList(playerMatLayout.getObjectives(), objectiveCards.size());

            // CREATE AND ADD PLAYER TO PLAYERS
            Player player = new Player(account, playerMat, factionMat,
                    playersCombatCards, playersObjectiveCards,
                    factionMatLayout.getPower(), playerMatLayout.getPopularity(), playerMatLayout.getCoins());

            players.add(player);
        }

        // CREATE HEXES
        List<Hex> hexes = new ArrayList<>();
        for (HexNode node : boardCache.getAll()) {
            hexes.add(new Hex(node));
        }

        // CREATE STRUCTURE BONUS
        List<StructureBonusType> structureBonusTypes = Arrays.asList(StructureBonusType.values());
        Collections.shuffle(structureBonusTypes);
        StructureBonusType chosenStructBonus = structureBonusTypes.get(0);

        // CREATE FACTORY CARDS
        List<Integer> factoryCards = generateFactoryCards();

        // CREATE ENCOUNTER CARDS
        List<Integer> encounterCards = generateEncounterCards();

        // CREATE DIALS
        PowerDial attacker = new PowerDial(true);
        PowerDial defender = new PowerDial(false);

        // CREATE TRIUMPH TRACK
        TriumphTrack triumphTrack = new TriumphTrack(Arrays.asList(
                new TriumphTrackSection(TriumphTrackSectionType.UPGRADES),
                new TriumphTrackSection(TriumphTrackSectionType.DEPLOYMENTS),
                new TriumphTrackSection(TriumphTrackSectionType.BUILDINGS),
                new TriumphTrackSection(TriumphTrackSectionType.UPGRADES),
                new TriumphTrackSection(TriumphTrackSectionType.WORKERS),
                new TriumphTrackSection(TriumphTrackSectionType.OBJECTIVE),
                new TriumphTrackSection(TriumphTrackSectionType.COMBAT_1),
                new TriumphTrackSection(TriumphTrackSectionType.COMBAT_2),
                new TriumphTrackSection(TriumphTrackSectionType.POPULARITY),
                new TriumphTrackSection(TriumphTrackSectionType.POWER)
                ));

        // GET CURRENT PLAYER
        Player currentPlayer = Collections.min(players,
                Comparator.comparing(player -> matLayoutCache.findByType(player.getPlayerMat().getType()).getStartOrder()));

        return new Game(players, hexes, combatCards, chosenStructBonus, factoryCards, encounterCards, attacker, defender, triumphTrack, currentPlayer);
    }

    private List<Integer> generateObjectiveCardNumbers() {
        List<Integer> res = IntStream.rangeClosed(1, 27)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(res);
        return res;
    }

    private List<Integer> generateFactoryCards() {
        List<Integer> res = IntStream.rangeClosed(1, 12)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(res);
        return res;
    }

    private List<Integer> generateEncounterCards() {
        List<Integer> res = IntStream.rangeClosed(1, 36)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(res);
        return res;
    }

    private List<Integer> generateCombatCards() {
        List<Integer> combatCards = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            combatCards.add(2);
        }
        for (int i = 0; i < 12; i++) {
            combatCards.add(3);
        }
        for (int i = 0; i < 8; i++) {
            combatCards.add(4);
        }
        for (int i = 0; i < 6; i++) {
            combatCards.add(5);
        }
        Collections.shuffle(combatCards);
        return combatCards;
    }

}
