import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { WorkProfilePage } from './work-profile.page';

describe('WorkProfilePage', () => {
  let component: WorkProfilePage;
  let fixture: ComponentFixture<WorkProfilePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkProfilePage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(WorkProfilePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
