import { Component, OnInit } from '@angular/core';
import { PopoverController } from '@ionic/angular';
import { LanguageService } from './../../services/language/language.service';

@Component({
  selector: 'app-language-popover',
  templateUrl: './language-popover.page.html',
  styleUrls: ['./language-popover.page.scss'],
})
export class LanguagePopoverPage implements OnInit {
  languages = [];
  selected = '' ;
  constructor(private popoverController: PopoverController , private langServ: LanguageService) { }

  ngOnInit() {
    this.languages = this.langServ.getLanguages();
    this.selected = this.langServ.selected;
  }

  selectLanguage(lng)
  {
    this.langServ.setLanguage(lng);
    this.popoverController.dismiss();
  }

}
