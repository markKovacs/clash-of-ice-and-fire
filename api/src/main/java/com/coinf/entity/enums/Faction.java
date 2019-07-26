package com.coinf.entity.enums;

import com.coinf.entity.blueprint.HexNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.coinf.entity.enums.HexType.*;

/**
 * Contains all the faction related constants,
 * including all information on faction mats as blueprints for all games.
 */
public enum Faction {

    TARGARYEN(Arrays.asList(VILLAGE, FARM),
            3, 2,
            "Daenerys & Tyrion", "House Targaryen",
            "Relentless",
            "You may choose the same section on your Player Mat as the previous turn(s)",
            "Riverwalk", "Move across rivers to farms and villages",
            "Township", "Move between any village you control and the factory",
            "People's Army", "In combat where you have at least 1 worker, you may play +1 Combat Card",
            "Speed", "+1 Hex per movement"),
    LANNISTER(Arrays.asList(FOREST, MOUNTAIN),
            1, 4,
            "Cersei & Jamie", "House Lannister",
            "Dominate",
            "There is no limit to the numbers of stars you can place from completing objectives and winning combat",
            "Riverwalk", "Move across rivers to forests and mountains",
            "Underpass", "Move between any mountain you control and any tunnel",
            "Disarm", "Before combat on a territory with a tunnel opponent gets -2 power",
            "Speed", "+1 Hex per movement"),
    STARK(Arrays.asList(FOREST, MOUNTAIN),
            4, 1,
            "Jon & Sansa", "House Stark",
            "Swim",
            "Your workersOnMat may move across rivers",
            "Riverwalk", "Move across rivers to forests and mountains",
            "Seaworthy", "Move to/from lakes and retreat onto adjacent lakes",
            "Artillery", "Before combat, if you play 1 power, opponent gets -2 power",
            "Speed", "+1 Hex per movement"),
    BARATHEON(Arrays.asList(FOREST, MOUNTAIN, TUNDRA, VILLAGE, FARM),
            0, 2,
            "Stannis & Melisandre", "House Baratheon",
            "Maifuku",
            "After moving your character, you may place an armed Trap token in its territory",
            "Toka", "Move across a river (max. 1 character or 1 mech per turn)",
            "Suiton", "Move to/from lakes; in combat on a lake, you may play +1 combat card",
            "Ronin", "Before combat where you have exactly 1 unit, gain +2 power",
            "Shinobi", "Move to any territory with a Trap token; you may arm the Trap"),
    GREYJOY(Arrays.asList(VILLAGE, MOUNTAIN),
            2, 3,
            "Balon & Yara", "House Greyjoy",
            "Meander",
            "Pick up to 2 options per encounter card",
            // TODO: 6-7 player addition: At the end of game, gain 3 coins for each encounter territory you control
            "Riverwalk", "Move across rivers to villages and mountains",
            "Submerge", "move to/from lakes and move from any lake to another",
            "Camaraderie", "in combat, do not lose popularity when forcing an opponent’s worker(s) to retreat",
            "Speed", "+1 Hex per movement"),
    MARTELL(Arrays.asList(TUNDRA, FARM),
            5, 0,
            "Doran & Areo Hotah", "House Martell",
            "Coercion",
            "Once per turn, you may spend 1 combat card as if it were any 1 resource token",
            "Riverwalk", "Move across rivers to farms and tundra",
            "Wayfare", "Move from a territory or home base to any inactive faction's home base or your own",
            // TODO: 6-7 player mode replacement: Move to any unoccupied farm
            "Scout", "Before combat, steal 1 of the opponent’s combat cards at random",
            "Speed", "+1 Hex per movement"),
    WHITE_WALKER(Collections.emptyList(),
            3, 0,
            "The Night King", "White Walkers",
            "Exalt",
            "After moving your character, you may place a Flag token on its territory",
            "Burrow", "Move across rivers to or from an adjacent tunnel territory",
            "Sword", "Before combat where you are attacking, opponent gets -2 power",
            "Shield", "Before combat where you are defending, gain +2 power",
            "Rally", "Move to any territory where you have a worker or Flag token");

    private final List<HexType> destinations;

    public final int power;
    public final int combatCards;
    public final String heroName;
    public final String houseName;
    public final String specName;
    public final String specDesc;
    public final String mech1Name;
    public final String mech1Desc;
    public final String mech2Name;
    public final String mech2Desc;
    public final String mech3Name;
    public final String mech3Desc;
    public final String mech4Name;
    public final String mech4Desc;

    Faction(List<HexType> destinations,
            int power, int combatCards,
            String heroName, String houseName,
            String specName, String specDesc,
            String mech1Name, String mech1Desc,
            String mech2Name, String mech2Desc,
            String mech3Name, String mech3Desc,
            String mech4Name, String mech4Desc) {
        this.destinations = destinations;
        this.power = power;
        this.combatCards = combatCards;
        this.heroName = heroName;
        this.houseName = houseName;
        this.specName = specName;
        this.specDesc = specDesc;
        this.mech1Name = mech1Name;
        this.mech1Desc = mech1Desc;
        this.mech2Name = mech2Name;
        this.mech2Desc = mech2Desc;
        this.mech3Name = mech3Name;
        this.mech3Desc = mech3Desc;
        this.mech4Name = mech4Name;
        this.mech4Desc = mech4Desc;
    }

    public boolean canRiverwalk(HexNode from, HexNode dest, boolean riverCrossed) {
        if (this.equals(WHITE_WALKER) && (from.isTunnel() || dest.isTunnel())) {
            return true;
        }
        if (this.equals(BARATHEON) && riverCrossed) {
            return true;
        }
        return this.destinations.contains(dest.getHexType());
    }

}
