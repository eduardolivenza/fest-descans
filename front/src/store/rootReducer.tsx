import { combineReducers } from "redux";
import { productsCollectionStateKey, productsCollectionReducer } from "pods/product-collection";
import { cartCollectionStateKey, cartCollectionReducer } from "pods/cart-view";

export const rootReducer = () =>
  combineReducers({
    [productsCollectionStateKey]: productsCollectionReducer,
    [cartCollectionStateKey]: cartCollectionReducer,
  });

