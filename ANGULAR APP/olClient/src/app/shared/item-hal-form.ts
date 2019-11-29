import { Embedded } from './Embedded';
import { Link } from './link';
import { Template } from './Template';
import { Page } from './page';
import { ItemDTO } from './ItemDTO';

export class ItemHalForm {
    // tslint:disable-next-line: variable-name
    _embedded: Embedded;
    // tslint:disable-next-line: variable-name
    _templates: Map<string, Template>;
    page: Page;
    number: number;
    name: string;
    amount: number;
    inventoryCode: string;
    // tslint:disable-next-line: variable-name
    _links: Map<string, Link>;

}
