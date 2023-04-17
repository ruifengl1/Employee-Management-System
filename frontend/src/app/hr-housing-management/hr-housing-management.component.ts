import { AdminHousingService } from '../services/admin-housing.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ChangeDetectorRef, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { catchError, of } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hr-housing-management',
  templateUrl: './hr-housing-management.component.html',
  styleUrls: ['./hr-housing-management.component.css']
})
export class HrHousingManagementComponent implements OnInit{

  constructor(private router: Router, private fb: FormBuilder, private http: HttpClient, private adminHousingService: AdminHousingService){
  }
  ngOnInit(): void {
    this.getAllHouse();
  }
  form = this.fb.group({
    address:  new FormControl('N boardway Drive', [Validators.required]),
    maxOccupant: new FormControl(5, [Validators.required,Validators.min(1)]),
    firstName: new FormControl('first', [Validators.required]),
    lastName: new FormControl('last', [Validators.required]),
    email: new FormControl('xxx@example.com', [Validators.required,Validators.email]),
    cellphone: new FormControl('1111111111', [Validators.required,Validators.minLength(10), Validators.maxLength(10)]),
    facilities: new FormArray([
      this.initFacility()
    ])
  });
  facilities = this.form.get('facilities') as FormArray;
  initFacility() {
    const newFacility = this.fb.group({
      facilityType: new FormControl('type'+ (Math.floor(Math.random() * 4)+1).toString(), [Validators.required]),
      description: new FormControl('desc'+(Math.floor(Math.random() * 4)+1).toString(), [Validators.required]),
      quantity: new FormControl(Math.floor(Math.random() * 4)+1, [Validators.required,Validators.min(1)])
    });
    return newFacility
  }
  addFacility() {
    const control = <FormArray>this.form.controls['facilities'];
    control.push(this.initFacility());
  }
  removeFacility(i: number) {
    const control = <FormArray>this.form.controls['facilities'];
    control.removeAt(i);
  }
  onSubmit() {
    console.log(this.form.value);
    
    if (!this.form.valid) {
      if(!this.form.get('facilities')?.valid){
        alert("The quantity should be greater than 0.")
      }
      else if(!this.form.get('maxOccupant')?.valid){
        alert("The Max Occupant should be greater than 0.")
      }
      else if(!this.form.get('email')?.valid){
        alert("Please enter the valid email.")
      }
      else if(!this.form.get('cellphone')?.valid){
        alert("Please enter the valid cellphone number.")
      }
      else{
        alert("Please check your form, all field are required. ");
      }
    }
    else {
      // let response = this.http.post('/housing-service/house/add', this.form.value);
      // response
      //   .pipe(catchError((err) => of({ err })))
      //   .subscribe((res: any) => {
      //     if (res.err) {
      //       alert('try later');
      //     } else {
      //       alert("A new house has been created!");
      //       this.form.reset()
      //       this.getAllHouse();
      //     }
      //   });
    }
  }
  allInfo:any=[];
  getAllHouse(){
    this.http.get('employeeHousing-service/houseDetailsWithResidentInfo')
      .subscribe((response) => {
        this.allInfo = response;
      });
  }
  deleteTheHouse(houseId:number){
    console.log(houseId);
    this.http.delete('housing-service/house/delete/'+houseId)
    .subscribe(() => {
      alert('Delete successful');
      this.getAllHouse();
  });
  }
  directToFacilityPage(houseId:number){
    console.log(houseId);
    this.adminHousingService.sethouseId(houseId);
    this.router.navigate(['/hr-housing-management/facility-report']);
  }
  openUserProfile(userId: string){
    localStorage.setItem('userStatus', JSON.stringify({
      isAdmin: true,
      isLogin: true,
      userId: userId
    }));
    window.open('./user-profile', "_blank");
  }
}
