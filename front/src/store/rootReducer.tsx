import { combineReducers } from "redux";
import { productsCollectionStateKey, productsCollectionReducer } from "pods/product-collection";



export const rootReducer = () =>
  combineReducers({
    [productsCollectionStateKey]: productsCollectionReducer,
  });

