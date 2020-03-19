import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EmployeeMySuffix } from 'app/shared/model/employee-my-suffix.model';
import { EmployeeMySuffixService } from './employee-my-suffix.service';
import { EmployeeMySuffixComponent } from './employee-my-suffix.component';
import { EmployeeMySuffixDetailComponent } from './employee-my-suffix-detail.component';
import { EmployeeMySuffixUpdateComponent } from './employee-my-suffix-update.component';
import { IEmployeeMySuffix } from 'app/shared/model/employee-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EmployeeMySuffixResolve implements Resolve<IEmployeeMySuffix> {
  constructor(private service: EmployeeMySuffixService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEmployeeMySuffix> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((employee: HttpResponse<EmployeeMySuffix>) => employee.body));
    }
    return of(new EmployeeMySuffix());
  }
}

export const employeeRoute: Routes = [
  {
    path: '',
    component: EmployeeMySuffixComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'jhipsterDemoApp.employee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EmployeeMySuffixDetailComponent,
    resolve: {
      employee: EmployeeMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterDemoApp.employee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EmployeeMySuffixUpdateComponent,
    resolve: {
      employee: EmployeeMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterDemoApp.employee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EmployeeMySuffixUpdateComponent,
    resolve: {
      employee: EmployeeMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterDemoApp.employee.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
