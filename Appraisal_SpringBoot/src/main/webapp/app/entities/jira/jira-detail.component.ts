import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJira } from 'app/shared/model/jira.model';

@Component({
  selector: 'jhi-jira-detail',
  templateUrl: './jira-detail.component.html',
})
export class JiraDetailComponent implements OnInit {
  jira: IJira | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ jira }) => (this.jira = jira));
  }

  previousState(): void {
    window.history.back();
  }
}
