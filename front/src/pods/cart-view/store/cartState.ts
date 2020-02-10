import { ProductCartEntityVm } from "core";

export const initState: cartState = {
    addedItems: []
}

export interface cartState {
    addedItems: ProductCartEntityVm[];
}