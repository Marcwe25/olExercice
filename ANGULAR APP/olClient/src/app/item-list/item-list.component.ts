import { Component, OnInit } from '@angular/core';
import { ItemService } from '../item.service';
import { Router } from '@angular/router';
import { ItemHalForm } from '../shared/item-hal-form';
import { ItemDTO } from '../shared/ItemDTO';
import { Link } from '../shared/link';
import * as $ from 'jquery';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  items: ItemHalForm;
  itemsArray: ItemDTO[];
  tempLink: Link;
  pageSize: number;
  service: ItemService;

  constructor(private itemService: ItemService,
              private router: Router) {
                this.service = itemService;
                this.pageSize = itemService.pageSize;
              }



  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.itemService.getItemsList().subscribe(result => {
      this.items = result;
      console.log(this.items);
      this.itemsArray = result._embedded.itemDTOSummaries;
    });
  }

  // tslint:disable-next-line: variable-name
  deleteitem(number: number) {
    this.itemService.deleteItem(number).subscribe(() => {
      this.reloadData();
      console.log('getting delete foooor ' + number);
    });
  }

  // tslint:disable-next-line: variable-name
  itemDetails(number: number) {
    this.router.navigate(['details', number]);
  }

  next() {
    this.tempLink = new Link();
    this.tempLink = this.getLinkWithRel('next');
    if (this.tempLink != null && Object.keys(this.tempLink).length > 0) {
    this.itemService.getItemsListFromLink(this.tempLink).subscribe(result => {
    this.items = result;
    this.itemsArray = result._embedded.itemDTOSummaries;
    });
    }
  }

  previous() {
    this.tempLink = new Link();
    this.tempLink = this.getLinkWithRel('prev');
    if (this.tempLink != null && Object.keys(this.tempLink).length > 0) {
      this.itemService.getItemsListFromLink(this.tempLink).subscribe(result => {
        this.items = result;
        this.itemsArray = result._embedded.itemDTOSummaries;
        });
    }
  }

  getLinkWithRel(rel: string) {
    for (const k in this.items._links) {
      if (k === rel) {return this.items._links[k]; }
    }
    return null;
  }

  setPageSize() {
     this.service.pageSize = this.pageSize;
     this.reloadData();
  }
}
