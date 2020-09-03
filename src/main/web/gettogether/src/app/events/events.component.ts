import { Component, OnInit } from '@angular/core';
import { select, Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Event } from '../services/event/event.model';
import { eventSelectAll } from './state/event.selectors';
import { loadEvents } from './state/event.actions';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.sass']
})
export class EventsComponent implements OnInit {

  events$: Observable<Event[]>;

  constructor(
    private store$: Store<any>
  ) { }

  ngOnInit(): void {
    const events: Event[] = [
      {
        name: 'Rec Game',
        time: new Date(),
        rsvps: [
          { attending: true, displayName: 'Charlie', userId: '1234' },
          { attending: true, displayName: 'Claire', userId: '1234' },
          { attending: true, displayName: 'Ryan', userId: '1234' },
          { attending: true, displayName: 'Mae', userId: '1234' },
        ],
        group: null,
      }
    ];

    this.store$.dispatch(loadEvents({ events }));

    this.events$ = this.store$.pipe(select(eventSelectAll));
  }
}
