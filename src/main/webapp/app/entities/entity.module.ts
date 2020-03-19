import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'bird',
        loadChildren: () => import('./bird/bird.module').then(m => m.JhipsterDemoBirdModule)
      },
      {
        path: 'department-my-suffix',
        loadChildren: () => import('./department-my-suffix/department-my-suffix.module').then(m => m.JhipsterDemoDepartmentMySuffixModule)
      },
      {
        path: 'employee-my-suffix',
        loadChildren: () => import('./employee-my-suffix/employee-my-suffix.module').then(m => m.JhipsterDemoEmployeeMySuffixModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterDemoEntityModule {}
