import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../item.service';
import { ItemValue } from '../shared/itemValue';

@Component({
  selector: 'app-update-item',
  templateUrl: './update-item.component.html',
  styleUrls: ['./update-item.component.css']
})
export class UpdateItemComponent implements OnInit {
  id: number;
  itemValue: ItemValue;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private itemService: ItemService) { }

  ngOnInit() {
    this.itemValue = {} as ItemValue;
    this.id = this.route.snapshot.params.number;

    this.itemService.getItem(this.id)
      .subscribe(data => {
        console.log(data);
        this.itemValue = {} as ItemValue;
        this.itemValue = data;
      }, error => console.log(error));
  }

  updateItemValue() {
    this.itemService.updateItem(this.id, this.itemValue)
      .subscribe(data => console.log(data), error => console.log(error));
    this.itemValue = {} as ItemValue;
    this.gotoItemDetails(this.id);
  }

  onSubmit() {
    this.updateItemValue();
  }

  // tslint:disable-next-line: variable-name
  gotoItemDetails(number: number) {
    this.router.navigate(['details', number]);
  }
  }
