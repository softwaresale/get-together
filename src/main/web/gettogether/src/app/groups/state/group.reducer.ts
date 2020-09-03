import { Action, createReducer, on } from '@ngrx/store';
import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';
import { Group } from '../../services/group/group.model';
import * as GroupActions from './group.actions';

export const groupsFeatureKey = 'groups';

export interface State extends EntityState<Group> {
  // additional entities state properties
}

export const adapter: EntityAdapter<Group> = createEntityAdapter<Group>();

export const initialState: State = adapter.getInitialState({
  // additional entity state properties
});


export const reducer = createReducer(
  initialState,
  on(GroupActions.addGroup,
    (state, action) => adapter.addOne(action.group, state)
  ),
  on(GroupActions.upsertGroup,
    (state, action) => adapter.upsertOne(action.group, state)
  ),
  on(GroupActions.addGroups,
    (state, action) => adapter.addMany(action.groups, state)
  ),
  on(GroupActions.upsertGroups,
    (state, action) => adapter.upsertMany(action.groups, state)
  ),
  on(GroupActions.updateGroup,
    (state, action) => adapter.updateOne(action.group, state)
  ),
  on(GroupActions.updateGroups,
    (state, action) => adapter.updateMany(action.groups, state)
  ),
  on(GroupActions.deleteGroup,
    (state, action) => adapter.removeOne(action.id, state)
  ),
  on(GroupActions.deleteGroups,
    (state, action) => adapter.removeMany(action.ids, state)
  ),
  on(GroupActions.loadGroups,
    (state, action) => adapter.setAll(action.groups, state)
  ),
  on(GroupActions.clearGroups,
    state => adapter.removeAll(state)
  ),
);


export const {
  selectIds,
  selectEntities,
  selectAll,
  selectTotal,
} = adapter.getSelectors();
