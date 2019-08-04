import { PlayerMat } from './playermat.interface';
import { FactionMat } from './factionmat.interface';
import { FactoryCard } from './factorycard.interface';

export interface Opponent {
  user: string;
  playerMat: PlayerMat;
  factionMat: FactionMat;
  coins: number;
  factoryCard: FactoryCard | null;
}
