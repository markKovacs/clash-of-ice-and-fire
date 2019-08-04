package com.coinf.service;

import com.coinf.dto.*;
import com.coinf.entity.blueprint.*;
import com.coinf.entity.enums.StructureBonusType;
import com.coinf.entity.instance.*;
import com.coinf.util.BoardCache;
import com.coinf.util.CardCache;
import com.coinf.util.MatLayoutCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    @Autowired
    private BoardCache boardCache;
    @Autowired
    private CardCache cardCache;
    @Autowired
    private MatLayoutCache matLayoutCache;

    public GameDto transform(Game game, String email) {

        GameDto gameDto = GameDto.builder()
                .id(game.getId())
                .hexes(createHexDtos(game.getHexes()))
                .popularityByUser(generateValuesByUser(game, Player::getPopularity))
                .powerByUser(generateValuesByUser(game, Player::getPower))
                .stars(createStarDtos(game))
                .player(createPlayerDto(game, email))
                .opponents(createOpponentDtos(game, email))
                .structureBonus(createStructureBonusDto(game.getStructureBonusType()))
                .build();

        return gameDto;
    }

    private StructureBonusDto createStructureBonusDto(StructureBonusType structureBonusType) {
        return StructureBonusDto.builder()
                .type(structureBonusType)
                .coinsByResult(cardCache.findStructBonusByType(structureBonusType).getEarnedCoinsByResult())
                .build();
    }

    private List<OpponentDto> createOpponentDtos(Game game, String email) {

        List<Player> opponents =  game.getPlayers().stream()
                .filter(p -> !email.equals(p.getAccount().getEmail()))
                .collect(Collectors.toList());

        List<OpponentDto> opponentDtos = new ArrayList<>();
        for (Player o : opponents) {
            FactoryCard factoryCard = null;
            if (o.getFactoryCardNum() != null) {
                factoryCard = cardCache.findFactoryCardByCardNumber(o.getFactoryCardNum());
            }
            OpponentDto opponentDto = OpponentDto.builder()
                    .user(o.getAccount().getUserName())
                    .playerMat(createPlayerMatDto(o.getPlayerMat()))
                    .factionMat(createFactionMatDto(o.getFactionMat()))
                    .coins(o.getCoins())
                    .factoryCard(factoryCard != null ? createFactoryCardDto(factoryCard) : null)
                    .build();

            opponentDtos.add(opponentDto);
        }

        return opponentDtos;
    }

    private PlayerDto createPlayerDto(Game game, String email) {
        Player player =  game.getPlayers().stream()
                .filter(p -> p.getAccount().getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No player found with email " + email));

        FactoryCard factoryCard = null;
        if (player.getFactoryCardNum() != null) {
            factoryCard = cardCache.findFactoryCardByCardNumber(player.getFactoryCardNum());
        }

        return PlayerDto.builder()
                .coins(player.getCoins())
                .user(player.getAccount().getUserName())
                .playerMat(createPlayerMatDto(player.getPlayerMat()))
                .factionMat(createFactionMatDto(player.getFactionMat()))
                .combatCards(player.getCombatCards())
                .objectiveCards(createObjectiveCardDtos(player.getObjectives()))
                .factoryCard(factoryCard != null ? createFactoryCardDto(factoryCard) : null)
                .build();
    }

    private FactoryCardDto createFactoryCardDto(FactoryCard factoryCard) {
        return FactoryCardDto.builder()
                .cardNum(factoryCard.getCardNum())
                .paymentTypeOne(factoryCard.getPaymentTypeOne())
                .paymentTypeTwo(factoryCard.getPaymentTypeTwo())
                .gainTypeOne(factoryCard.getGainTypeOne())
                .gainTypeTwo(factoryCard.getGainTypeTwo())
                .gainTypeThree(factoryCard.getGainTypeThree())
                .gainRelation(factoryCard.getGainRelation())
                .build();
    }

    private List<ObjectiveCardDto> createObjectiveCardDtos(List<Integer> objectives) {
        return objectives.stream()
                .map(id -> cardCache.findObjCardByCardNumber(id))
                .map(obj -> ObjectiveCardDto.builder()
                        .cardNumber(obj.getCardNumber())
                        .title(obj.getTitle())
                        .description(obj.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    private FactionMatDto createFactionMatDto(FactionMat factionMat) {

        FactionMatLayout layout = matLayoutCache.findByType(factionMat.getFaction());

        return FactionMatDto.builder()

                .faction(layout.getFaction())
                .power(layout.getPower())
                .combatCards(layout.getCombatCards())

                .heroName(layout.getHeroName())
                .houseName(layout.getHouseName())

                .mech1Name(layout.getMech1Name())
                .mech2Name(layout.getMech2Name())
                .mech3Name(layout.getMech3Name())
                .mech4Name(layout.getMech4Name())

                .mech1Desc(layout.getMech1Desc())
                .mech2Desc(layout.getMech2Desc())
                .mech3Desc(layout.getMech3Desc())
                .mech4Desc(layout.getMech4Desc())

                .specName(layout.getSpecName())
                .specDesc(layout.getSpecDesc())

                .powerUsed(factionMat.isPowerUsed())
                .coinUsed(factionMat.isCoinUsed())
                .popularityUsed(factionMat.isPopularityUsed())
                .combatCardUsed(factionMat.isCombatCardUsed())

                .mech1Deployed(factionMat.isMech1Deployed())
                .mech2Deployed(factionMat.isMech2Deployed())
                .mech3Deployed(factionMat.isMech3Deployed())
                .mech4Deployed(factionMat.isMech4Deployed())

                .build();
    }

    private PlayerMatDto createPlayerMatDto(PlayerMat playerMat) {

        PlayerMatLayout layout = matLayoutCache.findByType(playerMat.getType());

        return PlayerMatDto.builder()
                .currentSection(null) // NULL AT BEGINNING

                .sections(createPlayerMatSectionDtos(layout.getPlayerMatSections()))
                .type(playerMat.getType())
                .startOrder(layout.getStartOrder())
                .objectives(layout.getObjectives())
                .popularity(layout.getPopularity())
                .coins(layout.getCoins())

                .workers(playerMat.getWorkersOnMat())
                .produceUpgraded(playerMat.isProduceUpgraded())
                .millBuilt(playerMat.isMillBuilt())

                .tradeUpgraded(playerMat.isTradeUpgraded())
                .armoryBuilt(playerMat.isArmoryBuilt())

                .bolsterPowerUpgraded(playerMat.isBolsterPowerUpgraded())
                .bolsterCardUpgraded(playerMat.isBolsterCardUpgraded())
                .mineBuilt(playerMat.isMineBuilt())

                .upgradeUpgraded(playerMat.getUpgradeUpgraded())
                .deployUpgraded(playerMat.getDeployUpgraded())
                .buildUpgraded(playerMat.getBuildUpgraded())
                .enlistUpgraded(playerMat.getEnlistUpgraded())

                .upgradeEnlisted(playerMat.isUpgradeEnlisted())
                .deployEnlisted(playerMat.isDeployEnlisted())
                .buildEnlisted(playerMat.isBuildEnlisted())
                .enlistEnlisted((playerMat.isEnlistEnlisted()))

                .build();
    }

    private List<PlayerMatSectionDto> createPlayerMatSectionDtos(List<PlayerMatSection> playerMatSections) {
        return playerMatSections.stream()
                .map(section -> PlayerMatSectionDto.builder()
                        .position(section.getPosition())
                        .bottomType(section.getBottomRowAction().getBottomRowActionType())
                        .bottomFixedPayment(section.getBottomRowAction().getFixedPayment())
                        .bottomReducablePayment(section.getBottomRowAction().getReducablePayment())
                        .bottomGainableCoins(section.getBottomRowAction().getGainableCoins())
                        .topType(section.getTopRowAction().getTopRowActionType())
                        .build())
                .collect(Collectors.toList());
    }

    private List<HexDto> createHexDtos(List<Hex> hexes) {
        List<HexDto> hexDtos= new ArrayList<>();
        for (Hex hex : hexes) {
            Long hexNodeId = hex.getHexNodeId();
            HexNode node = boardCache.getHexNode(hexNodeId);

            HexDto hexDto = HexDto.builder()
                    .hexId(hex.getId())
                    .building(createBuildingDto(hex.getBuilding()))
                    .units(createUnitDtos(hex.getUnits()))
                    .encounterUsed(hex.getEncounterUsed())
                    .oil(hex.getOil())
                    .food(hex.getFood())
                    .steel(hex.getSteel())
                    .wood(hex.getSteel())
                    .flagged(hex.isFlagged())
                    .trap(hex.getTrap() != null ? hex.getTrap().getTrapType() : null)
                    .nodeId(hexNodeId)
                    .hexType(node.getHexType())
                    .weight(node.getWeight())
                    .encounter(node.isEncounter())
                    .isTunnel(node.isTunnel())
                    .name(node.getName())
                    .description(node.getDescription())
                    .build();

            hexDtos.add(hexDto);
        }
        return hexDtos;
    }

    private List<UnitDto> createUnitDtos(List<Unit> units) {
        return units.stream()
                .map(unit -> UnitDto.builder()
                        .id(unit.getId())
                        .type(unit.getUnitType())
                        .user(unit.getPlayer().getAccount().getUserName())
                        .build())
                .collect(Collectors.toList());
    }

    private BuildingDto createBuildingDto(Building building) {
        if (building == null) {
            return null;
        }
        return BuildingDto.builder()
                .buildingType(building.getBuildingType())
                .user(building.getPlayer().getAccount().getUserName())
                .build();
    }

    private Map<String, Integer> generateValuesByUser(Game game, Function<Player, Integer> function) {
        Map<String, Integer> valuesByUser = new HashMap<>();
        for (Player player : game.getPlayers()) {
            Integer value = function.apply(player);
            String userName = player.getAccount().getUserName();
            valuesByUser.put(userName, value);
        }
        return valuesByUser;
    }

    private List<StarDto> createStarDtos(Game game) {
        List<StarDto> stars = new ArrayList<>();
        for (TriumphTrackSection section : game.getTriumphTrack().getSections()) {
            int pos = section.getPosition();
            for (Star star : section.getStars()) {
                String user = star.getPlayer().getAccount().getUserName();
                stars.add(StarDto
                        .builder()
                        .position(pos)
                        .user(user)
                        .build());
            }
        }
        return stars;
    }

}
