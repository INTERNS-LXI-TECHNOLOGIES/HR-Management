/* tslint:disable */
import { Appraisal } from './appraisal';
import { Git } from './git';
import { Hackathon } from './hackathon';
import { LateArrival } from './late-arrival';
import { Leave } from './leave';
import { ReportStatus } from './report-status';
import { User } from './user';
export interface UserExtra {
  appraisals?: Array<Appraisal>;
  company?: string;
  dob?: string;
  gits?: Array<Git>;
  hackathons?: Array<Hackathon>;
  id?: number;
  image?: string;
  imageContentType?: string;
  joiningDate?: string;
  lateArrivals?: Array<LateArrival>;
  leaves?: Array<Leave>;
  position?: string;
  reportStatuses?: Array<ReportStatus>;
  user?: User;
  username?: string;
}
