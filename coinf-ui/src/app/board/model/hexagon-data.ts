export interface Hexagon {
  backgroundImage?: string;
  encounter?: 'ACTIVE' | 'USED';
  building?: string;
  units?: string[];
  resources?: string[];
  hexType: 'BLANK' | 'TUNDRA' | 'MOUNTAIN' | 'FOREST' | 'FARM' | 'VILLAGE' | 'LAKE' |
    'WHITEWALKER' | 'STARK' | 'BARATHEON' | 'TYRELL' | 'GREYJOY' | 'TARGARYEN' | 'LANNISTER' | 'IRONTHRONE';
}

