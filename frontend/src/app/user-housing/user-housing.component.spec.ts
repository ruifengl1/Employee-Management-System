import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserHousingComponent } from './user-housing.component';

describe('UserHousingComponent', () => {
  let component: UserHousingComponent;
  let fixture: ComponentFixture<UserHousingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserHousingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserHousingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
