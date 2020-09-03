import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventsRoutingModule } from './events-routing.module';
import { EventsComponent } from './events.component';
import { MatListModule } from '@angular/material/list';
import { StoreModule } from '@ngrx/store';
import * as fromEvent from './state/event.reducer';
import { EventCardComponent } from './event-card/event-card.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatGridListModule } from '@angular/material/grid-list';


@NgModule({
  declarations: [EventsComponent, EventCardComponent],
  imports: [
    CommonModule,
    EventsRoutingModule,
    MatListModule,
    StoreModule.forFeature(fromEvent.eventsFeatureKey, fromEvent.reducer),
    MatCardModule,
    MatButtonModule,
    MatGridListModule
  ]
})
export class EventsModule { }
