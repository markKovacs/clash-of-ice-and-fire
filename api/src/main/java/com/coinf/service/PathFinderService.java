package com.coinf.service;

import com.coinf.dto.HexNodeDepth;
import com.coinf.entity.blueprint.Edge;
import com.coinf.entity.blueprint.HexNode;
import com.coinf.entity.enums.BuildingType;
import com.coinf.entity.enums.Faction;
import com.coinf.entity.enums.HexType;
import com.coinf.entity.enums.UnitType;
import com.coinf.entity.instance.*;
import com.coinf.util.BoardCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service responsible for finding possible paths based on parameters.
 */
@Service
public class PathFinderService {

    @Autowired
    private BoardCache boardCache;

    public Set<HexNode> getDestinations(Unit unit, int range, Game game) {

        // TODO: it might be enough to get Unit as param, then we can get rest... will see

        if (unit == null || game == null || range < 1 || range > 3) {
            // TODO: Input parameters could be checked maybe through Spring framework bean validation
            //  Provide more details on the error
            //  A validation framework could be built as well
            throw new IllegalArgumentException("Wrong parameters.");
        }

        int riversCrossed = game.getRiversCrossedThisTurn();

        FactionMat factionMat = unit.getPlayer().getFactionMat();
        HexNode root = boardCache.findHexNode(unit.getHex().getHexNodeId());

        Set<HexNode> visited = new HashSet<>();
        Queue<HexNodeDepth> queue = new LinkedList<>();
        visited.add(root);
        queue.add(new HexNodeDepth(root, 0));

        while (!queue.isEmpty()) {
            HexNodeDepth currWithDepth = queue.remove();

            HexNode curr = currWithDepth.getHexNode();
            int currDepth = currWithDepth.getDepth();

            Collection<Edge> edges = curr.getNeighbours().values();

            // 1. CHECK ADJACENT HEXES

            for (Edge edge : edges) {
                HexNode dest = edge.getDestination();
                boolean notVisited = !visited.contains(dest);
                boolean notLake = !dest.getHexType().equals(HexType.LAKE);
                boolean noRiverInBetween = !edge.hasRiver();
                boolean riverwalkApplicable = isRiverwalkApplicable(curr, dest, factionMat, riversCrossed, unit);

                if (notVisited && notLake && (noRiverInBetween || riverwalkApplicable)) {
                    // VALID DESTINATION

                    visited.add(dest);

                    int currDepthPlusOne = currDepth + 1;
                    if (currDepthPlusOne < range) {
                        queue.add(new HexNodeDepth(dest, currDepthPlusOne));
                    }
                }
            }

            // 2. FACTION MAT SPECIALS

            if (UnitType.MECH_OR_CHAR.contains(unit.getUnitType())) {

                Set<HexNode> specials = getFactionSpecificDestinations(factionMat, game, edges, curr);

                visited.addAll(specials);

                int currDepthPlusOne = currDepth + 1;
                if (currDepthPlusOne < range) {
                    for (HexNode special : specials) {
                        HexNodeDepth hexNodeDepth = new HexNodeDepth(special, currDepthPlusOne);
                        if (!queue.contains(hexNodeDepth)) {
                            // MAKE SURE TO MAINTAIN A UNIQUE QUEUE
                            queue.add(new HexNodeDepth(special, currDepthPlusOne));
                        }

                    }
                }
            }

        }

        visited.remove(root);

        return visited;
    }

    /**
     * Either mech/character and has applicable riverwalk or a Stark worker.
     */
    private boolean isRiverwalkApplicable(HexNode from, HexNode dest, FactionMat factionMat, int riversCrossed, Unit unit) {
        Faction faction = factionMat.getFactionMatLayout().getFaction();
        if (factionMat.isMechOneDeployed() && UnitType.MECH_OR_CHAR.contains(unit.getUnitType())) {
            return faction.canRiverwalk(from, dest, riversCrossed);
        }
        return unit.getUnitType().equals(UnitType.WORKER) && faction.equals(Faction.STARK);
    }

    private Set<HexNode> getFactionSpecificDestinations(FactionMat factionMat, Game game, Collection<Edge> edges, HexNode curr) {
        // MECH/CHAR ALREADY ASSUMED

        List<HexNode> destinations = edges.stream().map(Edge::getDestination).collect(Collectors.toList());
        Player currPlayer = factionMat.getPlayer();

        Faction faction = factionMat.getFactionMatLayout().getFaction();
        Set<HexNode> specials = new HashSet<>();

        switch (faction) {

            case TYRELL:
                if (factionMat.isMechTwoDeployed()) {
                    if (game.getPlayers().size() < 6) {
                        // 1-5 PLAYER MODE
                        specials.add(boardCache.findUniqueHexNodeByType(HexType.TYRELL));

                        List<HexType> unusedHomes = game.getPlayers()
                                .stream()
                                .map(p -> p.getFactionMat().getFactionMatLayout().getFaction())
                                .map(f -> HexType.valueOf(f.name()))
                                .collect(Collectors.toList());

                        for (HexType home : unusedHomes) {
                            specials.add(boardCache.findUniqueHexNodeByType(home));
                        }

                    } else {
                        // 6-7 PLAYER MODE
                        List<HexNode> unoccupiedFarms = game.getHexes()
                                .stream()
                                .filter(hex -> hex.getBuilding() == null &&
                                        (hex.getUnits() == null || hex.getUnits().isEmpty()))
                                .map(hex -> boardCache.findHexNode(hex.getHexNodeId()))
                                .filter(hexNode -> hexNode.getHexType().equals(HexType.FARM))
                                .collect(Collectors.toList());

                        specials.addAll(unoccupiedFarms);
                    }
                }
                break;

            case STARK:
                if (factionMat.isMechTwoDeployed()) {
                    for (HexNode dest : destinations) {
                        // ADJACENT LAKES
                        if (dest.getHexType().equals(HexType.LAKE)) {
                            specials.add(dest);
                        }
                    }
                }
                break;

            case BARATHEON:
                if (factionMat.isMechTwoDeployed()) {
                    for (HexNode dest : destinations) {
                        // ADJACENT LAKES
                        if (dest.getHexType().equals(HexType.LAKE)) {
                            specials.add(dest);
                        }
                    }
                }
                if (factionMat.isMechFourDeployed()) {
                    // ALL NODES WITH TRAPS
                    List<HexNode> trappedNodes = game.getHexes().stream()
                            .filter(hex -> hex.getTrap() != null)
                            .map(hex -> boardCache.findHexNode(hex.getHexNodeId()))
                            .collect(Collectors.toList());
                    specials.addAll(trappedNodes);
                }
                break;

            case GREYJOY:
                if (factionMat.isMechTwoDeployed()) {
                    if (curr.getHexType().equals(HexType.LAKE)) {
                        // STANDING ON A LAKE
                        specials.addAll(boardCache.findHexNodesByType(HexType.LAKE));
                    } else {
                        // LAKES CANNOT BE ADJACENT TO EACH OTHER
                        for (HexNode dest : destinations) {
                            // FIND ADJACENT LAKES
                            if (dest.getHexType().equals(HexType.LAKE)) {
                                specials.add(dest);
                            }
                        }
                    }
                }
                break;

            case LANNISTER:
                if (factionMat.isMechTwoDeployed()) {

                    // FIND HEX NODE WHERE PLAYER BUILT A MINE
                    Building mine = currPlayer.getBuildings()
                            .stream()
                            .filter(b -> b.getBuildingType().equals(BuildingType.MINE))
                            .findFirst().orElse(null);

                    HexNode mineNode = null;
                    if (mine != null) {
                        mineNode = boardCache.findHexNode(mine.getHex().getHexNodeId());
                    }

                    if (curr.getHexType().equals(HexType.MOUNTAIN) || curr.isTunnel() || curr.equals(mineNode)) {
                        // STANDING ON A MOUNTAIN OR TUNNEL OR OWN MINE
                        List<HexNode> controlledMountains = getControlledByType(game, currPlayer, HexType.MOUNTAIN);
                        List<HexNode> tunnels = boardCache.getAllTunnels();

                        specials.addAll(controlledMountains);
                        specials.addAll(tunnels);
                        specials.add(mineNode);
                    }
                }
                break;

            case TARGARYEN:
                if (factionMat.isMechTwoDeployed()) {
                    if (curr.getHexType().equals(HexType.IRONTHRONE) || curr.getHexType().equals(HexType.VILLAGE)) {
                        // STANDING ON IRON THRONE OR A VILLAGE CONTROLLED BY PLAYER
                        List<HexNode> controlledVillages = getControlledByType(game, currPlayer, HexType.VILLAGE);

                        specials.addAll(controlledVillages);
                        specials.add(boardCache.findUniqueHexNodeByType(HexType.IRONTHRONE));
                    }
                }
                break;

            case WHITE_WALKER:
                if (factionMat.isMechFourDeployed()) {
                    // NODES WITH WORKERS OF CURRENT PLAYER
                    List<HexNode> nodesWithWorker = currPlayer.getUnits().stream()
                            .filter(unit -> unit.getUnitType().equals(UnitType.WORKER))
                            .map(w -> w.getHex().getHexNodeId())
                            .map(id -> boardCache.findHexNode(id))
                            .collect(Collectors.toList());

                    // FLAGGED NODES
                    List<HexNode> flaggedNodes = game.getHexes().stream()
                            .filter(Hex::isFlagged)
                            .map(hex -> boardCache.findHexNode(hex.getHexNodeId()))
                            .collect(Collectors.toList());

                    specials.addAll(nodesWithWorker);
                    specials.addAll(flaggedNodes);
                }

                break;
        }

        return specials;
    }

    private List<HexNode> getControlledByType(Game game, Player currPlayer, HexType hexType) {
        return game.getHexes()
                .stream()
                .filter(hex -> controlledByPlayer(hex, currPlayer))
                .map(hex -> boardCache.findHexNode(hex.getHexNodeId()))
                .filter(hexNode -> hexNode.getHexType().equals(hexType))
                .collect(Collectors.toList());
    }

    private boolean controlledByPlayer(Hex hex, Player currPlayer) {
        List<Unit> units = hex.getUnits();
        if (units != null && !units.isEmpty()) {
            // PLAYER ON TURN HAS UNIT ON HEX
            return currPlayer.equals(units.get(0).getPlayer());
        }

        // PLAYER ON TURN HAS BUILDING ON HEX
        Building building = hex.getBuilding();
        return building != null && currPlayer.equals(building.getPlayer());
    }

    // needs to be checked:
    // 1. not yet visited
    // AND
    // 2. not a lake (and not a home base, but this is already given as it is not a neighbor)
    // AND
    // 3:
    // A. adjacent,
    //      a. AND if hasRiver
    //      b. AND (a mech/char AND has riverwalk with criteria that is good) OR a worker AND is northern
    // OR (if mech or character)
    // B. if tyrell, and have wayfare on faction mat,
    //      AND 1-5 player, then home base or any inactive base
    //      OR 6-7 player, then move to any uncontrolled farm
    // OR
    // C. if stark, and have seaworthy on faction mat, then can move to lakes too
    // OR
    // D. if greyjoy, and have submerge on faction mat, then can move to lakes too, and from lakes to other lakes(no need control)
    // OR
    // E. if targaryen, and have township, then move between any village(control) and the factory(no need control)
    // OR
    // F. if lannister, and have underpass, then move between any mountain(control) and mine(no need control) and tunnel(no need control)
    // OR
    // G. if whitewwalker, and have rally, then move to any territory with a worker/flag token
    // OR
    // G. if baratheon, AND (if have shinobi, then move to any territory with a trap token
    //      OR if have suition, then move to lakes too

}
