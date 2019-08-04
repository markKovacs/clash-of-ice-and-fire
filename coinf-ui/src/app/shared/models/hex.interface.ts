import { Building } from './building.interface';
import { Unit } from './unit.interface';

export interface Hex {
  hexId: number;
  building: Building | null;
  units: Unit[];
  encounterUsed: boolean | null;
  oil: number;
  food: number;
  steel: number;
  wood: number;
  flagged: boolean;
  trap: string | null;

  nodeId: number;
  hexType: string;
  weight: number;
  encounter: boolean;
  isTunnel: boolean;
  name: string;
  description: string;
}
