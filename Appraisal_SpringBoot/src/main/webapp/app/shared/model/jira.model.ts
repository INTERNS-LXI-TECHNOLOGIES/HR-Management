import { Moment } from 'moment';
import { IUserExtra } from 'app/shared/model/user-extra.model';

export interface IJira {
  id?: number;
  date?: Moment;
  hour?: number;
  userExtra?: IUserExtra;
}

export class Jira implements IJira {
  constructor(public id?: number, public date?: Moment, public hour?: number, public userExtra?: IUserExtra) {}
}
