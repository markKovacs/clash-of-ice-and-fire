export interface FactionMat {
  faction: string;
  power: number;
  combatCards: number;

  heroName: string;
  houseName: string;

  mech1Name: string;
  mech2Name: string;
  mech3Name: string;
  mech4Name: string;

  mech1Desc: string;
  mech2Desc: string;
  mech3Desc: string;
  mech4Desc: string;

  specName: string;
  specDesc: string;

  powerUsed: boolean;
  coinUsed: boolean;
  popularityUsed: boolean;
  combatCardUsed: boolean;

  mech1Deployed: boolean;
  mech2Deployed: boolean;
  mech3Deployed: boolean;
  mech4Deployed: boolean;
}
