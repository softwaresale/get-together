import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GroupsRoutingModule } from './groups-routing.module';
import { GroupsComponent } from './groups.component';
import { StoreModule } from '@ngrx/store';
import * as fromGroup from './state/group.reducer';
import { GroupCardComponent } from './group-card/group-card.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';
import { MatGridListModule } from '@angular/material/grid-list';


@NgModule({
  declarations: [GroupsComponent, GroupCardComponent],
  imports: [
    CommonModule,
    GroupsRoutingModule,
    StoreModule.forFeature(fromGroup.groupsFeatureKey, fromGroup.reducer),
    MatCardModule,
    MatButtonModule,
    MatChipsModule,
    MatGridListModule
  ]
})
export class GroupsModule { }
