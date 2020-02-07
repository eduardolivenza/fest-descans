import { modelState } from "./modelState";
import { productsCollectionStateKey } from "pods/product-collection";

export const podProductsCollectionSelector = (state: { [productsCollectionStateKey]: modelState }) => {
    return state[productsCollectionStateKey];
};
  
/*
export const internalPortSelector = createSelector(
  podApacheSelector,
  (state: StoreModel.Apache) => state.internalPort
);
*/