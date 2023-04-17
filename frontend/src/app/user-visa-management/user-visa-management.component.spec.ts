import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserVisaManagementComponent } from './user-visa-management.component';

describe('UserVisaManagementComponent', () => {
  let component: UserVisaManagementComponent;
  let fixture: ComponentFixture<UserVisaManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserVisaManagementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserVisaManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
