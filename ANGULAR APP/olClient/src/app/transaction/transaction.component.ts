import { Component, OnInit } from '@angular/core';
import { Transaction } from '../shared/transaction';
import { ItemValue } from '../shared/itemValue';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  transaction: Transaction;
  itemValue: ItemValue;
  itemId: number;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private itemService: ItemService) { }

  ngOnInit() {
    this.itemValue = new ItemValue();
    this.itemId = this.route.snapshot.params.number;
    this.transaction = new Transaction();

    this.itemService.getItem(this.itemId)
      .subscribe(data => {
        this.itemValue = new ItemValue();
        this.itemValue.setValueFromItem(data);
      }, error => console.log(error));
  }

  updateTransactionValue() {
    this.itemService.updateTransaction(this.itemId, this.transaction)
      .subscribe(data => console.log(data), error => console.log(error));
    this.itemValue = new ItemValue();
    this.transaction = new Transaction();
    this.gotoItemDetails(this.itemId);
  }

  // tslint:disable-next-line: variable-name
  gotoItemDetails(number: number) {
    this.router.navigate(['details', number]);
  }

  onSubmit() {
    this.updateTransactionValue();
  }
}
