import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../item.service';
import { ItemHalForm } from '../shared/ItemHalForm';

@Component({
  selector: 'app-item-details',
  templateUrl: './item-details.component.html',
  styleUrls: ['./item-details.component.css']
})
export class ItemDetailsComponent implements OnInit {
  id: number;
  item: ItemHalForm;

  constructor(private route: ActivatedRoute, private router: Router,
              private itemService: ItemService) { }

  ngOnInit() {
    this.item = {} as ItemHalForm;

    this.id = this.route.snapshot.params.number;

    this.itemService.getItem(this.id)
      .subscribe(data => {
        console.log(data);
        this.item = data;
      }, error => console.log(error));
  }

  list() {
    this.router.navigate(['items']);
  }

  // tslint:disable-next-line: variable-name
  update(number: number) {
    this.router.navigate(['update', number]);
  }

  // tslint:disable-next-line: variable-name
  transaction(number: number) {
    this.router.navigate(['transaction', number]);
  }
}
