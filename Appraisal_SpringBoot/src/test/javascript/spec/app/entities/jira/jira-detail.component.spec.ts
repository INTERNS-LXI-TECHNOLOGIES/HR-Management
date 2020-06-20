import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppraisalTestModule } from '../../../test.module';
import { JiraDetailComponent } from 'app/entities/jira/jira-detail.component';
import { Jira } from 'app/shared/model/jira.model';

describe('Component Tests', () => {
  describe('Jira Management Detail Component', () => {
    let comp: JiraDetailComponent;
    let fixture: ComponentFixture<JiraDetailComponent>;
    const route = ({ data: of({ jira: new Jira(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppraisalTestModule],
        declarations: [JiraDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(JiraDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(JiraDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load jira on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.jira).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
