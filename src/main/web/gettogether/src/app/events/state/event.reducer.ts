import { Action, createReducer, on } from '@ngrx/store';
import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';
import * as EventActions from './event.actions';
import { Event } from '../../services/event/event.model';

export const eventsFeatureKey = 'events';

export interface State extends EntityState<Event> {
  // additional entities state properties
}

export const adapter: EntityAdapter<Event> = createEntityAdapter<Event>();

export const initialState: State = adapter.getInitialState({
  // additional entity state properties
});


export const reducer = createReducer(
  initialState,
  on(EventActions.addEvent,
    (state, action) => adapter.addOne(action.event, state)
  ),
  on(EventActions.upsertEvent,
    (state, action) => adapter.upsertOne(action.event, state)
  ),
  on(EventActions.addEvents,
    (state, action) => adapter.addMany(action.events, state)
  ),
  on(EventActions.upsertEvents,
    (state, action) => adapter.upsertMany(action.events, state)
  ),
  on(EventActions.updateEvent,
    (state, action) => adapter.updateOne(action.event, state)
  ),
  on(EventActions.updateEvents,
    (state, action) => adapter.updateMany(action.events, state)
  ),
  on(EventActions.deleteEvent,
    (state, action) => adapter.removeOne(action.id, state)
  ),
  on(EventActions.deleteEvents,
    (state, action) => adapter.removeMany(action.ids, state)
  ),
  on(EventActions.loadEvents,
    (state, action) => adapter.setAll(action.events, state)
  ),
  on(EventActions.clearEvents,
    state => adapter.removeAll(state)
  ),
);


export const eventSelectors = adapter.getSelectors();
