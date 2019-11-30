import { Link } from './link';

export interface ItemDTO {

    number: number;
    name: string;
    amount: number;
    inventoryCode: string;
    _links: Map<string, Link>;

}
