import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { LateArrivalPage } from './late-arrival.page';

describe('LateArrivalPage', () => {
  let component: LateArrivalPage;
  let fixture: ComponentFixture<LateArrivalPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LateArrivalPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(LateArrivalPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
