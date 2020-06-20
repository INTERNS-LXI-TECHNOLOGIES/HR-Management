import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJira } from 'app/shared/model/jira.model';
import { JiraService } from './jira.service';

@Component({
  templateUrl: './jira-delete-dialog.component.html',
})
export class JiraDeleteDialogComponent {
  jira?: IJira;

  constructor(protected jiraService: JiraService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.jiraService.delete(id).subscribe(() => {
      this.eventManager.broadcast('jiraListModification');
      this.activeModal.close();
    });
  }
}
