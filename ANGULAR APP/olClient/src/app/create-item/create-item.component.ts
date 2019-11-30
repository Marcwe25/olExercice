import { Component, OnInit } from '@angular/core';
import { ItemValue } from '../shared/itemValue';
import { ItemService } from '../item.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {

  itemValue: ItemValue = {} as ItemValue;
  submitted = false;

  constructor(private itemService: ItemService,
              private router: Router) { }

  ngOnInit() {
  }

  newItem(): void {
    this.submitted = false;
    this.itemValue = {} as ItemValue;
  }

  save() {
    this.itemService.createItem(this.itemValue)
      .subscribe(data => console.log(data), error => console.log(error));
    this.itemValue = {} as ItemValue;
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/items']);
  }
}

