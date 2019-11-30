import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ItemValue } from './shared/itemValue';
import { Transaction } from './shared/transaction';
import { URLSearchParams } from 'url';
import { ItemHalForm } from './shared/ItemHalForm';
import { Link } from './shared/link';
import { ItemDTO } from './shared/ItemDTO';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private baseUrl = 'http://localhost:8080/items';

  pageSize: number;
  pageNumber: number;

  constructor(private http: HttpClient) {
    this.pageSize = 5;
    this.pageNumber = 0;
  }

  getItem(id: number): Observable<ItemHalForm> {
    return this.http.get<ItemHalForm>(`${this.baseUrl}/${id}`);
  }

  createItem(item: ItemValue): Observable<any> {
    return this.http.post(`${this.baseUrl}`, item);
  }

  updateItem(id: number, value: ItemValue): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteItem(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getItemsList(): Observable<ItemHalForm> {
    return this.http.get<ItemHalForm>(`${this.baseUrl}`, {
      params: {
        size: this.pageSize.toString(),
        page: this.pageNumber.toString()
      }});
  }


  getItemsListFromLink(link: Link): Observable < ItemHalForm > {
    this.recordPageFromURLParams(link.href);
    return this.http.get<ItemHalForm>(link.href);
  }

  updateTransaction(id: number, value: Transaction): Observable < any > {
    return this.http.put(`${this.baseUrl}/${id}/transaction`, value);
  }

  recordPageFromURLParams(url: string) {
    const myParam = this.getParameterByName('page', url);
    if (myParam != null)  {
      this.pageNumber = Number(myParam);
    }
  }

  getParameterByName(name, url) {
    if (!url) { url = window.location.href; }
    name = name.replace(/[\[\]]/g, '\\$&');
    const regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)');
    const results = regex.exec(url);
    if (!results) { return null; }
    if (!results[2]) { return ''; }
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}
}
