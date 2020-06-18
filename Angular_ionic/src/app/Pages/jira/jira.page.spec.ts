import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { JiraPage } from './jira.page';

describe('JiraPage', () => {
  let component: JiraPage;
  let fixture: ComponentFixture<JiraPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JiraPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(JiraPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
