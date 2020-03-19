import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDepartmentMySuffix } from 'app/shared/model/department-my-suffix.model';
import { DepartmentMySuffixService } from './department-my-suffix.service';

@Component({
  templateUrl: './department-my-suffix-delete-dialog.component.html'
})
export class DepartmentMySuffixDeleteDialogComponent {
  department: IDepartmentMySuffix;

  constructor(
    protected departmentService: DepartmentMySuffixService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.departmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'departmentListModification',
        content: 'Deleted an department'
      });
      this.activeModal.dismiss(true);
    });
  }
}
