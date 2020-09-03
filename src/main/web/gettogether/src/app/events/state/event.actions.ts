import { createAction, props } from '@ngrx/store';
import { Update } from '@ngrx/entity';
import { Event } from '../../services/event/event.model';

export const loadEvents = createAction(
  '[Event/API] Load Events',
  props<{ events: Event[] }>()
);

export const addEvent = createAction(
  '[Event/API] Add Event',
  props<{ event: Event }>()
);

export const upsertEvent = createAction(
  '[Event/API] Upsert Event',
  props<{ event: Event }>()
);

export const addEvents = createAction(
  '[Event/API] Add Events',
  props<{ events: Event[] }>()
);

export const upsertEvents = createAction(
  '[Event/API] Upsert Events',
  props<{ events: Event[] }>()
);

export const updateEvent = createAction(
  '[Event/API] Update Event',
  props<{ event: Update<Event> }>()
);

export const updateEvents = createAction(
  '[Event/API] Update Events',
  props<{ events: Update<Event>[] }>()
);

export const deleteEvent = createAction(
  '[Event/API] Delete Event',
  props<{ id: string }>()
);

export const deleteEvents = createAction(
  '[Event/API] Delete Events',
  props<{ ids: string[] }>()
);

export const clearEvents = createAction(
  '[Event/API] Clear Events'
);
