import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrEmployeeProfileComponent } from './hr-employee-profile.component';

describe('HrEmployeeProfileComponent', () => {
  let component: HrEmployeeProfileComponent;
  let fixture: ComponentFixture<HrEmployeeProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HrEmployeeProfileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HrEmployeeProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
