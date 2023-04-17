import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityReportDetailComponent } from './facility-report-detail.component';

describe('FacilityReportDetailComponent', () => {
  let component: FacilityReportDetailComponent;
  let fixture: ComponentFixture<FacilityReportDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacilityReportDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilityReportDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
