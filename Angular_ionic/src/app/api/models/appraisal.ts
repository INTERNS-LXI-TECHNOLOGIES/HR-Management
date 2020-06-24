/* tslint:disable */
import { UserExtra } from './user-extra';
export interface Appraisal {
  attendance?: number;
  codeQuality?: number;
  companyPolicy?: number;
  date?: string;
  id?: number;
  meetingTargets?: number;
  punctuality?: number;
  userExtra?: UserExtra;
}
