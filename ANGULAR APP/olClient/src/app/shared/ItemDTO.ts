import { Link } from './link';

export class ItemDTO {
    number: number;
    name: string;
    amount: number;
    inventoryCode: string;
    // tslint:disable-next-line: variable-name
    _links: Map<string, Link>;
}
