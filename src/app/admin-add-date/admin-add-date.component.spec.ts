import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddDateComponent } from './admin-add-date.component';

describe('AdminAddDateComponent', () => {
  let component: AdminAddDateComponent;
  let fixture: ComponentFixture<AdminAddDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminAddDateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminAddDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
