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
    this.itemValue = {} as ItemValue;
    this.itemId = this.route.snapshot.params.number;
    this.transaction = {} as Transaction;

    this.itemService.getItem(this.itemId)
      .subscribe(data => {
        this.itemValue = {} as ItemValue;
        this.itemValue.amount = data.amount;
        this.itemValue.inventoryCode = data.inventoryCode;
        this.itemValue.name = data.name;
      }, error => console.log(error));
  }

  updateTransactionValue() {
    this.itemService.updateTransaction(this.itemId, this.transaction)
      .subscribe(data => console.log(data), error => console.log(error));
    this.itemValue =  {} as ItemValue;
    this.transaction = {} as Transaction;
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
