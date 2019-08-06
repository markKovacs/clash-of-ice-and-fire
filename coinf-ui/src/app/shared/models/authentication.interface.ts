export interface Authentication {
  authorities: string[];
  client_id: string;
  exp: number;
  guild: string;
  scope: string[];
  user_name: string;
}
