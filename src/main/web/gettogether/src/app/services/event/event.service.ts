import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseEntityService } from '../base-entity-service';
import { Event } from './event.model';

@Injectable({
  providedIn: 'root'
})
export class EventService extends BaseEntityService<Event>{

  constructor(http: HttpClient) {
    super(http, 'events');
  }
}
