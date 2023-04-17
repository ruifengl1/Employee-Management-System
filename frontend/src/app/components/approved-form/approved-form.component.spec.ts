import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovedFormComponent } from './approved-form.component';

describe('ApprovedFormComponent', () => {
  let component: ApprovedFormComponent;
  let fixture: ComponentFixture<ApprovedFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovedFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApprovedFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
