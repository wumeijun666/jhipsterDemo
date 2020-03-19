import { IEmployeeMySuffix } from 'app/shared/model/employee-my-suffix.model';

export interface IDepartmentMySuffix {
  id?: number;
  departmentName?: string;
  employees?: IEmployeeMySuffix[];
}

export class DepartmentMySuffix implements IDepartmentMySuffix {
  constructor(public id?: number, public departmentName?: string, public employees?: IEmployeeMySuffix[]) {}
}
