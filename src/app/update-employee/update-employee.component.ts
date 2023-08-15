import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from "@angular/material/snack-bar";
import { Employee } from "../shared/models/employee";
import { EmployeeService } from "../shared/employee.service";
import { ActivatedRoute, Router } from "@angular/router";
import { catchError, tap } from "rxjs";

@Component({
  selector: "app-update-employee",
  templateUrl: "./update-employee.component.html",
  styleUrls: ["./update-employee.component.scss"],
})
export class UpdateEmployeeComponent implements OnInit {
  public updateForm!: FormGroup;
  public horizontalPosition: MatSnackBarHorizontalPosition = "right";
  public verticalPosition: MatSnackBarVerticalPosition = "top";
  updatedEmployee: Employee = new Employee();
  id!: number;

  constructor(
    private _snackBar: MatSnackBar,
    private form: FormBuilder,
    private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.updateForm = form.group({
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
      emailId: ["", [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params["id"];
    this.employeeService.getEmployeeById(this.id).subscribe((res: any) => {
      this.updatedEmployee = res;
    });
  }

  navigateToLanding() {
    this.router.navigate(["/employees"]);
  }

  onUpdate() {
    this.employeeService
      .updateEmployee(this.id, this.updatedEmployee)
      .pipe(
        tap((res) => {
          this.updateData();
        }),
        catchError((err) => {
          console.log(err);
          return [err];
        })
      )
      .subscribe();
  }

  updateData() {
    this._snackBar.open("Updated successfully!", "X", {
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
    this.navigateToLanding();
  }
}
