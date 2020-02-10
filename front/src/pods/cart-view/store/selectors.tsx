
import { cartCollectionStateKey } from "pods/cart-view";
import { cartState } from "./cartState";

export const podCartCollectionSelector = (state: { [cartCollectionStateKey]: cartState }) => {
    return state[cartCollectionStateKey];
};
  
/*
export const internalPortSelector = createSelector(
  podApacheSelector,
  (state: StoreModel.Apache) => state.internalPort
);
*/