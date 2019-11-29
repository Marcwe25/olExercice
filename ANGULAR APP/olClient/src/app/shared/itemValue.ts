import {ItemHalForm } from '../shared/item-hal-form';


export class ItemValue {
    name: string;
    amount: number;
    inventoryCode: string;

    constructor() {}

    setValueFromItem(item: ItemHalForm) {
        this.name = item.name;
        this.amount = item.amount;
        this.inventoryCode = item.inventoryCode;
    }
}
