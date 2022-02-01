import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class ServiceRequestService {
  private baseUrl = "/api/service-requests";

  constructor(private http: HttpClient) {}

  getServiceRequest(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createServiceRequest(serviceRequest: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, serviceRequest);
  }

  updateServiceRequest(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteServiceRequest(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: "text" });
  }

  getServiceRequestsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
