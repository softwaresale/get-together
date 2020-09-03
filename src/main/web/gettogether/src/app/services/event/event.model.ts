import { Group } from '../group/group.model';

export interface RsvpStatus {
  userId: string;
  displayName: string;
  attending: boolean;
  id?: number;
}

export interface Event {
  id?: string;
  name: string;
  time: Date;
  rsvps: RsvpStatus[];
  group: Group;
}
