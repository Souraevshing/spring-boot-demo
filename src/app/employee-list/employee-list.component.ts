import { Component, OnInit } from "@angular/core";
import { Employee } from "../shared/models/employee";
import { EmployeeService } from "../shared/employee.service";
import { Router } from "@angular/router";
import { catchError, tap } from "rxjs";
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from "@angular/material/snack-bar";

@Component({
  selector: "app-employee-list",
  templateUrl: "./employee-list.component.html",
  styleUrls: ["./employee-list.component.scss"],
})
export class EmployeeListComponent implements OnInit {
  public horizontalPosition: MatSnackBarHorizontalPosition = "right";
  public verticalPosition: MatSnackBarVerticalPosition = "top";
  employees: Employee[] = [];
  step: number = 0;

  constructor(
    private _snackBar: MatSnackBar,
    private employeeService: EmployeeService,
    private route: Router
  ) {}

  ngOnInit(): void {
    this.employeeService.getAllEmployees().subscribe((res: Employee[]) => {
      this.employees = res;
    });
  }

  onView(id: number) {
    this.route.navigate(["update-employee", id]);
  }

  updateEmployee(id: number) {
    this.route.navigate(["update-employee", id]);
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe((res) => {
      this.deleteData();
    });
  }

  deleteData() {
    this._snackBar.open("Deleted successfully!", "X", {
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
    this.employeeService.getAllEmployees();
    this.navigateToLanding();
  }

  navigateToLanding() {
    this.route.navigate(["/employees"]);
  }
}
