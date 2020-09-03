import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

/**
 * Represents a base entity service
 * T - type of entity
 * U - id
 */
export abstract class BaseEntityService<T> {

  private readonly entityEndpoint: string;

  protected constructor(private http: HttpClient, pathEndpoint: string, apiVersion = 'v1') {
    this.entityEndpoint = `${environment.apiBaseUrl}/api/${apiVersion}/${pathEndpoint}`;
  }

  getAll(): Observable<T[]> {
    return this.http.get<T[]>(this.entityEndpoint);
  }

  getById(id: string | number): Observable<T> {
    return this.http.get<T>(`${this.entityEndpoint}/${id}`);
  }

  create(entity: T): Observable<T> {
    return this.http.post<T>(this.entityEndpoint, entity);
  }
}
