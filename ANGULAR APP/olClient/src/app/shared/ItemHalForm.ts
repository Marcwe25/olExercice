import { Link } from './link';
import { Embedded } from './Embedded';
import { Template } from './Template';
import { Page } from './Page';

export interface ItemHalForm {

        _embedded: Embedded;
        _templates: Map<string, Template>;
        page: Page;
        number: number;
        name: string;
        amount: number;
        inventoryCode: string;
        _links: Map<string, Link>;
}
