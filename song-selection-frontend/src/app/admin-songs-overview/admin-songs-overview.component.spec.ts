import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AdminSongsOverviewComponent} from './admin-songs-overview.component';

describe('AdminSongsOverviewComponent', () => {
  let component: AdminSongsOverviewComponent;
  let fixture: ComponentFixture<AdminSongsOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminSongsOverviewComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AdminSongsOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
