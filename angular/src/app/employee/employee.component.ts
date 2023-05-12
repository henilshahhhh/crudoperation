import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Employee } from './employeemodel';
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  employeemodel: Array<Employee> | undefined ;
  public display: boolean = false;
  public show1: boolean = true;
  public Add: any = 'Show';
  public Edit: any = 'Show';

  addform: FormGroup;
  updateform: FormGroup;


  emp_age: number | undefined;
  emp_name: string;
  emp_date: string
  employee: never[];
  emp_id: any;
  id: any;

  constructor(
    private httpClient: HttpClient,
    private formbuilder: FormBuilder
  ) {
    this.employee = [];
    this.emp_age = undefined;
    this.emp_name = '';
    this.emp_date = '';
    this.addform = this.formbuilder.group({});
    this.updateform = this.formbuilder.group({});

  }

 
  ngOnInit(): void {
    this.initform();
    this.getEmployeeList();
  }

  initform() {
    this.addform = this.formbuilder.group({
      emp_name: new FormControl('', Validators.required),
      emp_age: new FormControl('', Validators.required),

      emp_date: new FormControl('', Validators.required)
    });
  }

  submitForm() {
    this.httpClient.post<Array<Employee>>("http://localhost:8005/emp/savedata", this.addform.value).subscribe((response) => {
      console.log(response);
      window.location.reload();
    })
  }

  getEmployeeList() {
    this.httpClient.get<Array<Employee>>("http://localhost:8005/emp/viewdata").subscribe((employee) => {
      this.employeemodel = employee;
    })
  }

  deleteEmp(id: number) {
    this.httpClient.delete<Array<Employee>>("http://localhost:8005/emp/delete" + "/" + id).subscribe((employee) => {
      console.log(id);
    })
    window.location.reload();
    this.getEmployeeList();
  }

  editEmp(employee: Employee) {
      console.log("edit method called");
      this.display = true;
      this.show1 = false;
     
      this.addform.setValue({ emp_name: employee.emp_name, emp_age: employee.emp_age, emp_date: employee.emp_date })
  
    }
  updateEmp(id: number) {
    let body = {
      emp_name: this.addform.get('emp_name')?.value,
      emp_age: this.addform.get('emp_age')?.value,
      emp_date: this.addform.get('emp_date')?.value
    };
  
    this.httpClient.post<Employee>(`http://localhost:8005/emp/update/${id}`, body).subscribe((data) => {
      console.log(data);
    });
    this.getEmployeeList();
    window.location.reload();
  }
  // editEmp(id:number,employee: Employee) {
  //   console.log("edit method called");
  //   this.display = true;
  //   this.show1 = false;
  //   console.log(this.id)
  //   this.addform.setValue({ emp_name: employee.emp_name, emp_age: employee.emp_age, emp_date: employee.emp_date })

  // }

  // updateEmp() {

  //   let body1 = {
  //    "emp_id": this.addform.get('emp_id')?.value,
    
  //     "emp_name": this.addform.get('emp_name')?.value,
  //     "emp_age": this.addform.get('emp_age')?.value,
      
  //     "emp_date": this.addform.get('emp_date')?.value

  //   }

  //   this.httpClient.post<Array<Employee>>("http://localhost:8005/emp/update", body1).subscribe((employee) => {
  //     console.log(this.emp_id);

  //   })
  //   this.getEmployeeList();
  //   // window.location.reload();
  // }


}
