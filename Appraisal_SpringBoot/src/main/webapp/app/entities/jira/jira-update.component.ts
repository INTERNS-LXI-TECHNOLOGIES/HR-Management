import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IJira, Jira } from 'app/shared/model/jira.model';
import { JiraService } from './jira.service';
import { IUserExtra } from 'app/shared/model/user-extra.model';
import { UserExtraService } from 'app/entities/user-extra/user-extra.service';

@Component({
  selector: 'jhi-jira-update',
  templateUrl: './jira-update.component.html',
})
export class JiraUpdateComponent implements OnInit {
  isSaving = false;
  userextras: IUserExtra[] = [];
  dateDp: any;

  editForm = this.fb.group({
    id: [],
    date: [],
    hour: [],
    userExtra: [],
  });

  constructor(
    protected jiraService: JiraService,
    protected userExtraService: UserExtraService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ jira }) => {
      this.updateForm(jira);

      this.userExtraService.query().subscribe((res: HttpResponse<IUserExtra[]>) => (this.userextras = res.body || []));
    });
  }

  updateForm(jira: IJira): void {
    this.editForm.patchValue({
      id: jira.id,
      date: jira.date,
      hour: jira.hour,
      userExtra: jira.userExtra,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const jira = this.createFromForm();
    if (jira.id !== undefined) {
      this.subscribeToSaveResponse(this.jiraService.update(jira));
    } else {
      this.subscribeToSaveResponse(this.jiraService.create(jira));
    }
  }

  private createFromForm(): IJira {
    return {
      ...new Jira(),
      id: this.editForm.get(['id'])!.value,
      date: this.editForm.get(['date'])!.value,
      hour: this.editForm.get(['hour'])!.value,
      userExtra: this.editForm.get(['userExtra'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IJira>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUserExtra): any {
    return item.id;
  }
}
