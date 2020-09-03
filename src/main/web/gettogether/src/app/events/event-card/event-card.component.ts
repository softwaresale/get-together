import { Component, Input, OnInit } from '@angular/core';
import { Event } from '../../services/event/event.model';

@Component({
  selector: 'app-event-card',
  templateUrl: './event-card.component.html',
  styleUrls: ['./event-card.component.sass']
})
export class EventCardComponent implements OnInit {

  @Input()
  event: Event;

  constructor() { }

  ngOnInit(): void {
  }

  get attendees(): string {
    return this.event.rsvps
      .filter(rsvp => rsvp.attending)
      .map(rsvp => rsvp.displayName)
      .join(', ');
  }
}
