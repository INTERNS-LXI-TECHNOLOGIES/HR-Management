/* tslint:disable */
import { GrantedAuthority } from './granted-authority';
export interface Authentication {
  authenticated?: boolean;
  authorities?: Array<GrantedAuthority>;
  credentials?: {};
  details?: {};
  name?: string;
  principal?: {};
}
