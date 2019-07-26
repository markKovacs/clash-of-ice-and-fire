package com.coinf.util;

import com.coinf.entity.blueprint.HexNode;
import com.coinf.entity.blueprint.ObjectiveCard;
import com.coinf.entity.enums.GameEventType;
import com.coinf.entity.enums.HexType;
import com.coinf.entity.enums.UnitType;
import com.coinf.entity.instance.*;
import com.coinf.service.GameService;
import com.coinf.service.HexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Component
public class ObjectiveCardEnricher {

    private final static Logger LOG = Logger.getLogger(ObjectiveCardEnricher.class);

    @Autowired
    private BoardCache boardCache;
    @Autowired
    private HexService hexService;
    @Autowired
    private GameService gameService;


    public void enrichWithIsCompleted(ObjectiveCard card) {
        LOG.info("Enriching objective card #" + card.getCardNumber());
        card.setIsCompleted(getPredicateForCardNumber(card.getCardNumber()));
    }

    private BiPredicate<Game, Player> getPredicateForCardNumber(Integer cardNumber) {

        BiPredicate<Game, Player> isCompleted;

        switch (cardNumber) {
            case 1:
                isCompleted = this::case1;
                break;
            case 2:
                isCompleted = this::case2;
                break;
            case 3:
                isCompleted = this::case3;
                break;
            case 4:
                isCompleted = this::case4;
                break;
            case 5:
                isCompleted = this::case5;
                break;
            case 6:
                isCompleted = this::case6;
                break;
            case 7:
                isCompleted = this::case7;
                break;
            case 8:
                isCompleted = this::case8;
                break;
            case 9:
                isCompleted = this::case9;
                break;
            case 10:
                isCompleted = this::case10;
                break;
            case 11:
                isCompleted = this::case11;
                break;
            case 12:
                isCompleted = this::case12;
                break;
            case 13:
                isCompleted = this::case13;
                break;
            case 14:
                isCompleted = this::case14;
                break;
            case 15:
                isCompleted = this::case15;
                break;
            case 16:
                isCompleted = this::case16;
                break;
            case 17:
                isCompleted = this::case17;
                break;
            case 18:
                isCompleted = this::case18;
                break;
            case 19:
                isCompleted = this::case19;
                break;
            case 20:
                isCompleted = this::case20;
                break;
            case 21:
                isCompleted = this::case21;
                break;
            case 22:
                isCompleted = this::case22;
                break;
            case 23:
                isCompleted = this::case23;
                break;
            case 24:
                isCompleted = this::case24;
                break;
            case 25:
                isCompleted = this::case25;
                break;
            case 26:
                isCompleted = this::case26;
                break;
            case 27:
                isCompleted = this::case27;
                break;
            default:
                throw new IllegalStateException("Objective cards have been initialized improperly.");
        }

        return isCompleted;
    }

    private boolean case1(Game game, Player player) {
        // Control at least 3 mountain territories at the end of your turn.
        return controlAtLeast3OfTypeEndOfTurn(game, player, HexType.MOUNTAIN);
    }

    private boolean case2(Game game, Player player) {
        // Control at least 3 tunnels at the end of your turn.
        if (game.turnEnded()) {
            List<HexNode> controlledTunnels = hexService.getAllControlledTunnels(game, player);
            return CollectionUtils.hasAtLeast3(controlledTunnels);
        }
        return false;
    }

    private boolean case3(Game game, Player player) {
        // Control at least 3 farm territories at the end of your turn.
        return controlAtLeast3OfTypeEndOfTurn(game, player, HexType.FARM);
    }

    private boolean case4(Game game, Player player) {
        // Control at least 3 tundra territories at the end of your turn.
        return controlAtLeast3OfTypeEndOfTurn(game, player, HexType.TUNDRA);
    }

    private boolean case5(Game game, Player player) {
        // Control the Factory at the end of your turn and have the highest power of any player (ties are okay).
        Long ironThroneNodeId = boardCache.findUniqueHexNodeByType(HexType.IRONTHRONE).getId();
        Hex ironThrone = hexService.getHexForGameAndNodeId(game, ironThroneNodeId);
        return ironThrone.isControlledBy(player) && hasHighestPower(game, player);
    }

    private boolean case6(Game game, Player player) {
        // Force an opponent’s worker to retreat and still have at least 7 power.
        if (player.getPower() >= 7) {
            return gameService.eventHappened(game, GameEventType.WORKER_FORCED_OUT);
        }
        return false;
    }

    private boolean case7(Game game, Player player) {
        // Have 1 Factory card, at least 1 mech, and no more than 3 workersOnMat.
        return player.getFactoryCard() != null
                && player.getFactionMat().deployedOnce()
                && player.getPlayerMat().workerCount() <= 3;
    }

    private boolean case8(Game game, Player player) {
        // Have $2 or less, at least 4 workersOnMat, and at least 7 popularity.
        return player.getCoins() <= 2
                && player.getPopularity() >= 7
                && player.getPlayerMat().workerCount() >= 4;
    }

    private boolean case9(Game game, Player player) {
        // Control a territory with 9 or more resource tokens (at least 1 of each type) on it at the end of your turn.
        if (game.turnEnded()) {
            return hexService.getAllControlledHexes(game, player)
                    .stream()
                    .anyMatch(hex -> hex.hasAllDistinctResources() && hex.getSumOfResources() >= 9);
        }
        return false;
    }

    private boolean case10(Game game, Player player) {
        // Control at least 3 forest territories at the end of your turn.
        return controlAtLeast3OfTypeEndOfTurn(game, player, HexType.FOREST);
    }

    private boolean case11(Game game, Player player) {
        // Control at least 3 village territories at the end of your turn.
        return controlAtLeast3OfTypeEndOfTurn(game, player, HexType.VILLAGE);
    }

    private boolean case12(Game game, Player player) {
        // Have at least $20 at the end of your turn.
        if (game.turnEnded()) {
            return player.getCoins() >= 20;
        }
        return false;
    }

    private boolean case13(Game game, Player player) {
        // Have at least 6 of your tokens (i.e., character, workersOnMat, mechs, and structures) on 1 territory.
        return game.getHexes()
                .stream()
                .anyMatch(hex -> hex.getSumOfTokensForPlayer(player) >= 6);
    }

    private boolean case14(Game game, Player player) {
        // Have at least 1 upgrade, 1 mech, 1 structure, 1 recruit, and 1 of each resource at the end of your turn.
        if (game.turnEnded()) {
            PlayerMat playerMat = player.getPlayerMat();
            FactionMat factionMat = player.getFactionMat();
            if (playerMat.builtOnce() && playerMat.enlistedOnce() &&
                    playerMat.upgradedOnce() && factionMat.deployedOnce()) {
                return hexService.getAllControlledHexes(game, player)
                        .stream()
                        .anyMatch(Hex::hasAllDistinctResources);
            }
        }
        return false;
    }

    private boolean case15(Game game, Player player) {
        // Have the same number of workersOnMat and recruits.
        return player.getPlayerMat().workerCount() == player.getPlayerMat().recruitCount();
    }

    private boolean case16(Game game, Player player) {
        // Control a territory with your character, exactly 1 mech, exactly 1 worker, and at least 5 resources at the end of your turn.
        if (game.turnEnded()) {
            Hex hex = player.getUnits()
                    .stream()
                    .filter(unit -> unit.ofType(UnitType.CHARACTER))
                    .findFirst()
                    .orElseThrow(()-> new InvalidStateException("All players must have a character."))
                    .getHex();

            if (hex.getUnits().size() == 3 && hex.getSumOfResources() >= 5) {
                boolean mechFound = false;
                boolean workerFound = false;
                for (Unit unit : hex.getUnits()) {
                    if (unit.ofType(UnitType.MECH)) {
                        mechFound = true;
                    } else if (unit.ofType(UnitType.WORKER)) {
                        workerFound = true;
                    }
                }
                return mechFound && workerFound;
            }
        }
        return false;
    }

    private boolean case17(Game game, Player player) {
        // Have 0 Factory cards and control at least 2 territories adjacent to the Factory at the end of your turn.
        if (game.turnEnded()) {
            if (player.getFactoryCard() == null) {
                List<Hex> hexes = getHexesAroundIronThrone(game);

                return hexes.stream()
                        .filter(hex -> hex.isControlledBy(player))
                        .count() >= 2;
            }
        }
        return false;
    }

    private boolean case18(Game game, Player player) {
        // Have 0 power, at least 13 popularity, and at least 5 workersOnMat.
        return player.getPower() == 0 &&
                player.getPopularity() >= 13 &&
                player.getPlayerMat().workerCount() >= 5;
    }

    private boolean case19(Game game, Player player) {
        // Control at least 5 territories surrounding the same lake at the end of your turn.
        List<HexNode> lakeNodes = hexService.getControlledByType(game, player, HexType.LAKE);
        for (HexNode lakeNode : lakeNodes) {
            List<Long> ids = lakeNode.getAdjacentNodes()
                    .stream()
                    .map(HexNode::getId)
                    .collect(Collectors.toList());

            long controlledAroundLake = hexService.getHexesForGameAndNodeIds(game, ids)
                    .stream()
                    .filter(hex -> hex.isControlledBy(player))
                    .count();

            if (controlledAroundLake >= 5) {
                return true;
            }
        }
        return false;
    }

    private boolean case20(Game game, Player player) {
        // Build at least 3 structures, have at least 7 popularity, and have 0 mechs.
        return player.getPlayerMat().buildingCount() == 3 &&
                player.getPopularity() >= 7 &&
                !player.getFactionMat().deployedOnce();
    }

    private boolean case21(Game game, Player player) {
        // Control at least 9 tokens of the same resource at the end of your turn.
        if (game.turnEnded()) {
            int sumOfOil = 0;
            int sumOfSteel = 0;
            int sumOfFood = 0;
            int sumOfWood = 0;
            for (Hex hex : hexService.getAllControlledHexes(game, player)) {
                sumOfOil += hex.getOil();
                sumOfSteel += hex.getSteel();
                sumOfFood += hex.getFood();
                sumOfWood += hex.getWood();
                if (sumOfOil >= 9 || sumOfSteel >= 9 || sumOfFood >= 9 || sumOfWood >= 9) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean case22(Game game, Player player) {
        // Have 1 Factory card and 0 upgrades.
        return player.getFactoryCard() != null && player.getPlayerMat().upgradeCount() == 0;
    }

    private boolean case23(Game game, Player player) {
        // Have 8 or more combat cards in hand and at least 1 combat victory star.
        return game.getCombatCards().size() >= 8 && !CollectionUtils.isEmpty(player.getStars());
    }

    private boolean case24(Game game, Player player) {
        // Have at least 5 workersOnMat on a territory adjacent to the Factory at the end of your turn.
        if (game.turnEnded()) {
            List<Long> ids = player.getUnits()
                    .stream()
                    .filter(unit -> unit.ofType(UnitType.WORKER))
                    .map(unit -> unit.getHex().getHexNodeId())
                    .collect(Collectors.toList());

            int counter = 0;
            for (Long id : ids) {
                for (HexNode node : boardCache.getHexNode(id).getAdjacentNodes()) {
                    if (node.ofType(HexType.IRONTHRONE)) {
                        counter++;
                        break;
                    }
                }
                if (counter >= 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean case25(Game game, Player player) {
        // Have 3 or less popularity, at least 13 power, and at least 2 mechs.
        return player.getPopularity() <= 3 &&
                player.getPower() >= 13 &&
                player.getFactionMat().mechCount() >= 2;
    }

    private boolean case26(Game game, Player player) {
        // Control at least 1 of each primary terrain type with at least 1 worker on each at the end of your turn.
        if (game.turnEnded()) {
            Set<Long> ids = player.getUnits()
                    .stream()
                    .filter(unit -> unit.ofType(UnitType.WORKER))
                    .map(unit -> unit.getHex().getHexNodeId())
                    .collect(Collectors.toSet());

            Set<HexType> types = new HashSet<>();
            for (Long id : ids) {
                types.add(boardCache.getHexNode(id).getHexType());
            }
            return types.containsAll(HexType.PRIMARY_TERRAINS);
        }
        return false;
    }

    private boolean case27(Game game, Player player) {
        // Build at least 3 structures, none of which are adjacent to your home base.
        int buildings = player.getPlayerMat().buildingCount();
        if (buildings < 3) {
            return false;
        }

        List<Long> ids = player.getBuildings()
                .stream()
                .map(b -> b.getHex().getHexNodeId())
                .collect(Collectors.toList());

        for (Long id : ids) {
            HexType homeBaseType = HexType.fromFaction(player.getFaction());
            boolean adjacentToHomeBase = boardCache.getHexNode(id).getAdjacentNodes()
                    .stream()
                    .anyMatch(hexNode -> hexNode.ofType(homeBaseType));
            if (adjacentToHomeBase) {
                return false;
            }
        }
        return true;
    }

    private List<Hex> getHexesAroundIronThrone(Game game) {
        List<Long> ids = boardCache.findUniqueHexNodeByType(HexType.IRONTHRONE).getAdjacentNodes()
                .stream()
                .map(HexNode::getId)
                .collect(Collectors.toList());

        return hexService.getHexesForGameAndNodeIds(game, ids);
    }

    private boolean controlAtLeast3OfTypeEndOfTurn(Game game, Player player, HexType type) {
        if (game.turnEnded()) {
            List<HexNode> controlledTerritories = hexService.getControlledByType(game, player, type);
            return CollectionUtils.hasAtLeast3(controlledTerritories);
        }
        return false;
    }

    private boolean hasHighestPower(Game game, Player player) {
        int max = game.getPlayers()
                .stream()
                .map(Player::getPower)
                .collect(Collectors.summarizingInt(Integer::intValue))
                .getMax();

        return player.getPower() >= max;
    }

}
