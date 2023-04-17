import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {UserStatusService} from "../../services/user-status.service";
import {HttpClient} from "@angular/common/http";
import {Validators} from "@angular/forms";
import { Router } from '@angular/router';

@Component({
  selector: 'app-pending-form',
  templateUrl: './pending-form.component.html',
  styleUrls: ['./pending-form.component.css']
})
export class PendingFormComponent implements OnInit {
  constructor(private readonly fb: FormBuilder, private readonly uss: UserStatusService, private readonly http: HttpClient,private router: Router) {
  }

  pendingForm: any;

  ngOnInit(): void {
    const userId = this.uss.userStatus.userId;
    // const userId = 139;
    this.pendingForm = this.fb.group({
      "userID": [userId],
      "firstName": ["", Validators.required],
      "lastName": ["", Validators.required],
      "middleName": [""],
      "preferredName": [""],

      "cellPhone": ["", Validators.required],
      "alternatePhone": [""],
      "email": ["", Validators.required],
      "ssn": ["", Validators.required],
      "dob": ["", Validators.required],
      "gender": [""],

      "driverLicense" : [""],
      "driverLicenseExpiration" : [""],


      // Addresses
      "addressLine1": ["", Validators.required],
      "addressLine2": [""],
      "city": ["", Validators.required],
      "state": ["", Validators.required],
      "zipCode": ["", Validators.required],

      // Contacts
      "c_firstName": ["", Validators.required],
      "c_lastName": ["", Validators.required],
      "c_email" : ["", Validators.required],
      "c_cellPhone" : ["", Validators.required],
      "c_alternatePhone" : [""],
      "c_relationship" : ["", Validators.required],


    })

  }

  onSubmit() {
    // console.log(this.pendingForm.get("firstName").errors)
    // console.log(this.pendingForm.get("lastName").errors)
    // console.log(this.pendingForm.get("middleName").errors)
    // console.log(this.pendingForm.invalid)


    //assign new house
    //house id = ??


    const res = this.pendingForm.value;
    const { userID, firstName, lastName, middleName, preferredName, cellPhone, alternatePhone, email, ssn, dob, gender }  = res;
    const { addressLine1, addressLine2, city, state, zipCode} = res;

    const contact = {
      firstName: res.c_firstName,
      lastName: res.c_lastName,
      email: res.c_email,
      cellPhone: res.c_cellPhone,
      alternatePhone: res.c_alternatePhone,
      relationship: res.c_relationship
    };

    const employeeBody = {
      userID: userID,
      firstName: firstName,
      lastName: lastName,
      middleName: middleName,
      preferredName: preferredName,
      cellPhone: cellPhone,
      alternatePhone: alternatePhone,
      email: email,
      ssn: ssn,
      dob: dob,
      gender: gender,
      personalDocuments : [
          {
            path: "work-authorization-documents/fake_OPT_Receipt.txt",
            title: "OPT Recepit",
            documentType: "visa"
          },
          {
            path: "work-authorization-documents/driver_license",
            title: "driver_license",
            documentType: "driver_license"
          }
      ]

    }

// post a new employee
    this.http.post("/employee-service/employee", employeeBody).subscribe(
      (response) => {
        console.log("Successfully created employee:", response);

        // Add a new address to this employee
        this.http.put(`/employee-service/employee/${userID}/address`, { addressLine1, addressLine2, city, state, zipCode }).subscribe(
          (response) => {
            console.log("add address to this employee", response);

            // Add a new contact to this employee
            this.http.put(`/employee-service/employee/${userID}/contact`, contact).subscribe(
              (response) => {
                console.log("add contact to this employee", response);
              },
              (error) => {
                console.error("Error adding contact", error);
              }
            );
          },
          (error) => {
            console.error("Error adding address", error);
          }
        );
      },
      (error) => {
        console.error("Error creating employee:", error);
      }
    );

    const status = {
      status: "PENDING",
    };

    //change application status to PENDING
    this.http.patch(`/application-service/application/${userID}/status`, status)
      .subscribe(
      (response) => {
        console.log("change status", response);
      },
      (error) => {
        console.error("Error when change status", error);
      }
    );


    //get assigned houseId
    const houseId = this.http.get('/employeeHousing-service/getAvailableHouseId');
    const body = {
      "houseID": houseId
    }

    //add house id to employee
    this.http.patch(`/employee-service/employee/${userID}`, body)
      .subscribe(
        (response) => {
          console.log("add houseId", response);
        },
        (error) => {
          console.error("add houseId", error);
        }
      );

    this.uss.onStatusChange({
      isAdmin: false,
      isLogin: true,
      userId: userID,
      applicationStatus: "PENDING"
    });


    //redirect to user-visa
    this.router.navigate(['/user-visa-management']);




  }


}

