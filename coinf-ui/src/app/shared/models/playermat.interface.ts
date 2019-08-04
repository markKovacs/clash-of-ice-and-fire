import { PlayerMatSection } from './playermatsection.interface';

export interface PlayerMat {
  currentSection: number | null;

  sections: PlayerMatSection[];
  type: string;
  startOrder: number;
  objectives: number;
  popularity: number;
  coins: number;

  workers: number;
  produceUpgraded: boolean;
  millBuilt: boolean;

  tradeUpgraded: boolean;
  armoryBuilt: boolean;

  bolsterPowerUpgraded: boolean;
  bolsterCardUpgraded: boolean;
  monumentBuilt: boolean;

  moveUpgraded: boolean;
  gainUpgraded: boolean;
  mineBuilt: boolean;

  upgradeUpgraded: number;
  deployUpgraded: number;
  buildUpgraded: number;
  enlistUpgraded: number;

  upgradeEnlisted: boolean;
  deployEnlisted: boolean;
  buildEnlisted: boolean;
  enlistEnlisted: boolean;
}
