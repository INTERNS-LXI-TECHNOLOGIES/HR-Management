import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppraisalTestModule } from '../../../test.module';
import { JiraUpdateComponent } from 'app/entities/jira/jira-update.component';
import { JiraService } from 'app/entities/jira/jira.service';
import { Jira } from 'app/shared/model/jira.model';

describe('Component Tests', () => {
  describe('Jira Management Update Component', () => {
    let comp: JiraUpdateComponent;
    let fixture: ComponentFixture<JiraUpdateComponent>;
    let service: JiraService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppraisalTestModule],
        declarations: [JiraUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(JiraUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(JiraUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(JiraService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Jira(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Jira();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
