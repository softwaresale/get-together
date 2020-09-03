import { createFeatureSelector, createSelector } from '@ngrx/store';
import { eventSelectors, eventsFeatureKey } from './event.reducer';

export const eventFeatureStateSelector = createFeatureSelector(eventsFeatureKey);

export const eventSelectAll = createSelector(
  eventFeatureStateSelector,
  eventSelectors.selectAll
);

export const eventSelectEntities = createSelector(
  eventFeatureStateSelector,
  eventSelectors.selectEntities
);

export const eventSelectIds = createSelector(
  eventFeatureStateSelector,
  eventSelectors.selectIds
);

export const eventSelectTotal = createSelector(
  eventFeatureStateSelector,
  eventSelectors.selectTotal
);
