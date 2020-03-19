import { Moment } from 'moment';
import { IDepartmentMySuffix } from 'app/shared/model/department-my-suffix.model';

export interface IEmployeeMySuffix {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  phoneNumber?: string;
  hireDate?: Moment;
  salary?: number;
  commissionPct?: number;
  department?: IDepartmentMySuffix;
}

export class EmployeeMySuffix implements IEmployeeMySuffix {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public phoneNumber?: string,
    public hireDate?: Moment,
    public salary?: number,
    public commissionPct?: number,
    public department?: IDepartmentMySuffix
  ) {}
}
