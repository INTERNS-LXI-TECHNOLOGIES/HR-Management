import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppraisalTestModule } from '../../../test.module';
import { JiraComponent } from 'app/entities/jira/jira.component';
import { JiraService } from 'app/entities/jira/jira.service';
import { Jira } from 'app/shared/model/jira.model';

describe('Component Tests', () => {
  describe('Jira Management Component', () => {
    let comp: JiraComponent;
    let fixture: ComponentFixture<JiraComponent>;
    let service: JiraService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppraisalTestModule],
        declarations: [JiraComponent],
      })
        .overrideTemplate(JiraComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(JiraComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(JiraService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Jira(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.jiras && comp.jiras[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
