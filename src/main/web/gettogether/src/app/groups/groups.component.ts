import { Component, OnInit } from '@angular/core';
import { Group } from '../services/group/group.model';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.sass']
})
export class GroupsComponent implements OnInit {

  groups: Group[] = [
    {
      id: 1,
      name: 'Rec Volleyball',
      members: [],
      leader: {
        name: 'Charlie Sale',
        nickname: 'Chuck',
        picture: '',
        id: 'charliesaleid',
      },
      events: [
        {
          name: 'Match Play',
          group: null,
          rsvps: [],
          time: new Date(),
          id: '123'
        },
        {
          name: 'Rec Play',
          group: null,
          rsvps: [],
          time: new Date(),
          id: '123'
        },
      ],
      description: 'Just a group of college students that play volleyball here and there'
    },
    {
      id: 2,
      name: 'Purdue Hackers',
      members: [],
      leader: {
        name: 'Someone Else',
        nickname: 'Someone',
        picture: '',
        id: 'charliesaleid',
      },
      events: [
        {
          name: 'Hackathon',
          group: null,
          rsvps: [],
          time: new Date(),
          id: '123'
        },
        {
          name: 'Kotlin Workshop',
          group: null,
          rsvps: [],
          time: new Date(),
          id: '123'
        },
      ],
      description: 'A whole lot of CS nerds',
    },
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
