import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IJira } from 'app/shared/model/jira.model';
import { JiraService } from './jira.service';
import { JiraDeleteDialogComponent } from './jira-delete-dialog.component';

@Component({
  selector: 'jhi-jira',
  templateUrl: './jira.component.html',
})
export class JiraComponent implements OnInit, OnDestroy {
  jiras?: IJira[];
  eventSubscriber?: Subscription;

  constructor(protected jiraService: JiraService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.jiraService.query().subscribe((res: HttpResponse<IJira[]>) => (this.jiras = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInJiras();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IJira): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInJiras(): void {
    this.eventSubscriber = this.eventManager.subscribe('jiraListModification', () => this.loadAll());
  }

  delete(jira: IJira): void {
    const modalRef = this.modalService.open(JiraDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.jira = jira;
  }
}
