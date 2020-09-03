import { User } from '../user/user.model';
import { Event } from '../event/event.model';

export interface Group {
  id?: number;
  name: string;
  members: User[];
  leader: User;
  description?: string;
  events: Event[];
}
