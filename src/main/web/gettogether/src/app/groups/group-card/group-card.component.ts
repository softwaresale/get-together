import { Component, Input, OnInit } from '@angular/core';
import { Group } from '../../services/group/group.model';

@Component({
  selector: 'app-group-card',
  templateUrl: './group-card.component.html',
  styleUrls: ['./group-card.component.sass']
})
export class GroupCardComponent implements OnInit {

  @Input()
  group: Group;

  constructor() { }

  ngOnInit(): void {
  }

  get upcomingEvents(): string {
    return this.group.events.map(event => event.name).join(', ');
  }
}
