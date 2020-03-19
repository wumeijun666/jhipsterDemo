import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DepartmentMySuffix } from 'app/shared/model/department-my-suffix.model';
import { DepartmentMySuffixService } from './department-my-suffix.service';
import { DepartmentMySuffixComponent } from './department-my-suffix.component';
import { DepartmentMySuffixDetailComponent } from './department-my-suffix-detail.component';
import { DepartmentMySuffixUpdateComponent } from './department-my-suffix-update.component';
import { IDepartmentMySuffix } from 'app/shared/model/department-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class DepartmentMySuffixResolve implements Resolve<IDepartmentMySuffix> {
  constructor(private service: DepartmentMySuffixService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDepartmentMySuffix> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((department: HttpResponse<DepartmentMySuffix>) => department.body));
    }
    return of(new DepartmentMySuffix());
  }
}

export const departmentRoute: Routes = [
  {
    path: '',
    component: DepartmentMySuffixComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterDemoApp.department.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DepartmentMySuffixDetailComponent,
    resolve: {
      department: DepartmentMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterDemoApp.department.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DepartmentMySuffixUpdateComponent,
    resolve: {
      department: DepartmentMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterDemoApp.department.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DepartmentMySuffixUpdateComponent,
    resolve: {
      department: DepartmentMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterDemoApp.department.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
