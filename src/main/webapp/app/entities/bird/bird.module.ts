import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { BirdComponent } from './bird.component';
import { BirdDetailComponent } from './bird-detail.component';
import { BirdUpdateComponent } from './bird-update.component';
import { BirdDeleteDialogComponent } from './bird-delete-dialog.component';
import { birdRoute } from './bird.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(birdRoute)],
  declarations: [BirdComponent, BirdDetailComponent, BirdUpdateComponent, BirdDeleteDialogComponent],
  entryComponents: [BirdDeleteDialogComponent]
})
export class JhipsterDemoBirdModule {}
