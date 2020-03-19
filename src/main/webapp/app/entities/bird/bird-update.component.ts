import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IBird, Bird } from 'app/shared/model/bird.model';
import { BirdService } from './bird.service';

@Component({
  selector: 'jhi-bird-update',
  templateUrl: './bird-update.component.html'
})
export class BirdUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    add: [null, [Validators.required, Validators.maxLength(100)]],
    age: [null, [Validators.required, Validators.min(18)]],
    seven:[null,[Validators.required]]
  });

  constructor(protected birdService: BirdService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ bird }) => {
      this.updateForm(bird);
    });
  }

  updateForm(bird: IBird) {
    this.editForm.patchValue({
      id: bird.id,
      name: bird.name,
      add: bird.add,
      age: bird.age,
      seven: bird.seven
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const bird = this.createFromForm();
    if (bird.id !== undefined) {
      this.subscribeToSaveResponse(this.birdService.update(bird));
    } else {
      this.subscribeToSaveResponse(this.birdService.create(bird));
    }
  }

  private createFromForm(): IBird {
    return {
      ...new Bird(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      add: this.editForm.get(['add']).value,
      age: this.editForm.get(['age']).value,
      seven: this.editForm.get(['seven']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBird>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
