import {
  all,
  fork
} from "redux-saga/effects";
import { productsSaga } from "pods/product-collection/productsSaga";

export function* mainSaga() {
  console.log("Main saga executed");
  yield all([fork(productsSaga)]);
}