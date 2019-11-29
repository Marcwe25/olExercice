import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemDetailsComponent } from './item-details/item-details.component';
import { CreateItemComponent } from './create-item/create-item.component';
import { ItemListComponent } from './item-list/item-list.component';
import { UpdateItemComponent } from './update-item/update-item.component';
import { TransactionComponent } from './transaction/transaction.component';

const routes: Routes = [
  { path: '', redirectTo: 'items', pathMatch: 'full' },
{ path: 'items', component: ItemListComponent },
{ path: 'add', component: CreateItemComponent },
{ path: 'update/:number', component: UpdateItemComponent },
{ path: 'details/:number', component: ItemDetailsComponent },
{ path: 'transaction/:number', component: TransactionComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
