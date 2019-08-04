import { Hex } from './hex.interface';
import { Opponent } from './opponent.interface';
import { Player } from './player.interface';
import { StructureBonus } from './structurebonus.interface';
import { Star } from './star.interface';

export interface Game {
  id: number;
  hexes: Hex[];
  opponents: Opponent[];
  player: Player;
  popularityByUser: Map<string, number>;
  powerByUser: Map<string, number>;
  structureBonus: StructureBonus;
  stars: Star[];
}
