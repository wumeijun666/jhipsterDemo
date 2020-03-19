import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmployeeMySuffix } from 'app/shared/model/employee-my-suffix.model';
import { EmployeeMySuffixService } from './employee-my-suffix.service';

@Component({
  templateUrl: './employee-my-suffix-delete-dialog.component.html'
})
export class EmployeeMySuffixDeleteDialogComponent {
  employee: IEmployeeMySuffix;

  constructor(
    protected employeeService: EmployeeMySuffixService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.employeeService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'employeeListModification',
        content: 'Deleted an employee'
      });
      this.activeModal.dismiss(true);
    });
  }
}
