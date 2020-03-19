import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBird } from 'app/shared/model/bird.model';
import { BirdService } from './bird.service';

@Component({
  templateUrl: './bird-delete-dialog.component.html'
})
export class BirdDeleteDialogComponent {
  bird: IBird;

  constructor(protected birdService: BirdService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.birdService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'birdListModification',
        content: 'Deleted an bird'
      });
      this.activeModal.dismiss(true);
    });
  }
}
