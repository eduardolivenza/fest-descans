import { ProductEntitySizeVm, ProductEntityVm } from "core";

export interface ProductCartEntityVm {
    product: ProductEntityVm;
    selectedSize: ProductEntitySizeVm;
    quantity: number;
}