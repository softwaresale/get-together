import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Group } from './group.model';
import { BaseEntityService } from '../base-entity-service';

@Injectable({
  providedIn: 'root'
})
export class GroupService extends BaseEntityService<Group>{

  constructor(http: HttpClient) {
    super(http, 'groups');
  }
}
