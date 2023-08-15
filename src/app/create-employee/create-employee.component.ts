import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Employee } from "../shared/models/employee";
import { EmployeeService } from "../shared/employee.service";
import { Router } from "@angular/router";
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from "@angular/material/snack-bar";
import { catchError, tap } from "rxjs/operators";

@Component({
  selector: "app-create-employee",
  templateUrl: "./create-employee.component.html",
  styleUrls: ["./create-employee.component.scss"],
})
export class CreateEmployeeComponent {
  public createForm!: FormGroup;
  public horizontalPosition: MatSnackBarHorizontalPosition = "right";
  public verticalPosition: MatSnackBarVerticalPosition = "top";
  employee: Employee = new Employee();

  constructor(
    private form: FormBuilder,
    private employeeService: EmployeeService,
    private route: Router,
    private _snackBar: MatSnackBar
  ) {
    this.createForm = form.group({
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
      emailId: ["", [Validators.required, Validators.email]],
    });
  }

  onSubmit() {
    this.employeeService
      .createEmployee(this.employee)
      .pipe(
        tap((res) => {
          this.saveData();
        }),
        catchError((err) => {
          console.log(err);
          return [err]; // Handle the error case as needed
        })
      )
      .subscribe();
  }

  saveData() {
    this._snackBar.open("Saved successfully!", "X", {
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
    this.navigateToLanding();
  }

  navigateToLanding() {
    this.route.navigate(["/employees"]);
  }
}
