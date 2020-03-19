import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Bird } from 'app/shared/model/bird.model';
import { BirdService } from './bird.service';
import { BirdComponent } from './bird.component';
import { BirdDetailComponent } from './bird-detail.component';
import { BirdUpdateComponent } from './bird-update.component';
import { IBird } from 'app/shared/model/bird.model';

@Injectable({ providedIn: 'root' })
export class BirdResolve implements Resolve<IBird> {
  constructor(private service: BirdService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBird> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((bird: HttpResponse<Bird>) => bird.body));
    }
    return of(new Bird());
  }
}

export const birdRoute: Routes = [
  {
    path: '',
    component: BirdComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'jhipsterDemoApp.bird.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BirdDetailComponent,
    resolve: {
      bird: BirdResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterDemoApp.bird.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BirdUpdateComponent,
    resolve: {
      bird: BirdResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterDemoApp.bird.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BirdUpdateComponent,
    resolve: {
      bird: BirdResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterDemoApp.bird.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
