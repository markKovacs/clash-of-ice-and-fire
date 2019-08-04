import { FactoryCard } from './factorycard.interface';
import { ObjectiveCard } from './objectivecard.interface';
import { PlayerMat } from './playermat.interface';
import { FactionMat } from './factionmat.interface';

export interface Player {
  coins: number;
  user: string;
  playerMat: PlayerMat;
  factionMat: FactionMat;
  combatCards: number[];
  objectiveCards: ObjectiveCard[];
  factoryCard: FactoryCard | null;
}
