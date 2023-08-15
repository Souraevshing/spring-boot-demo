import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Employee } from "./models/employee";
import { environment } from "../environment/environment";

@Injectable({ providedIn: "root" })
export class EmployeeService {
  constructor(private http: HttpClient) {}

  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(environment.api);
  }

  createEmployee(employee: Employee): Observable<Object> {
    return this.http.post(environment.api, employee);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${environment.api}/${id}`);
  }

  updateEmployee(id: number, employee: Employee): Observable<Object> {
    return this.http.put<Employee>(`${environment.api}/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<Object> {
    return this.http.delete(`${environment.api}/${id}`);
  }
}
