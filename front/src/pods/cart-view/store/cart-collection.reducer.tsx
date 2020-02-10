import { cartState, initState} from "./cartState";
import { actions } from "pods/cart-view/constants";
import { BaseAction} from "store/base-actions";
import { ProductCartEntityVm } from "core";

function addToCart(state: cartState, productAdded: ProductCartEntityVm) {
  if (productAdded.selectedSize === null){
    console.error("size is null");
    return {...state};
  }
  let existed_item = state.addedItems.find(
    item =>
      item.product.internalIdentifier ===
        productAdded.product.internalIdentifier &&
      item.selectedSize.size === productAdded.selectedSize.size
  );
  //check if the action id exists in the addedItem
  if (existed_item) {
    existed_item.quantity += 1;
    return {
      ...state
    };
  } else {
    productAdded.quantity = 1;
    return {
      ...state,
      addedItems: [...state.addedItems, productAdded]
    };
  }
}

export const cartCollectionReducer = (
  state: cartState = initState,
  action: BaseAction
) => {
  switch (action.type) {
    case actions.ADD_TO_CART:
      return addToCart(state, action.payload);
    default:
      return state;
  }
};
