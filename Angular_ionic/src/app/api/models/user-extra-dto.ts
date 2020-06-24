/* tslint:disable */
import { Authority } from './authority';
export interface UserExtraDTO {
  authorities?: Array<Authority>;
  company?: string;
  dob?: string;
  email?: string;
  firstName?: string;
  id?: number;
  image?: string;
  imageContentType?: string;
  joiningDate?: string;
  lastName?: string;
  login?: string;
  position?: string;
}
