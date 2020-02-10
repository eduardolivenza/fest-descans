import { BaseAction } from "store/base-actions";
import { ProductEntityVm, ProductEntitySizeVm } from "core";
import { actions } from "pods/cart-view";

export const addToCartAction = (product: ProductEntityVm, selectedSize: ProductEntitySizeVm): BaseAction => ({
  type: actions.ADD_TO_CART,
  payload: {
    product,
    selectedSize,
  },
});
