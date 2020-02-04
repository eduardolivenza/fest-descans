import { call, put, takeLatest, all, fork } from "redux-saga/effects";
import { actions } from "pods/product-collection/constants";
import { getProductsCollection } from "core/api/product-collection.api";
import { ProductEntityApi } from "core/apiModel/product-entity.api";
import { mapFromApiToVm } from "core/mapper/product-entity.mapper";
import { mapToCheckBoxValue } from "core/mapper/category-entity.mapper";
import { mapFromAToBCollection } from "common";
import {
  fetchedProductsAction,
  fetchedCategoriesAction
} from "pods/product-collection/actions";
import { getCategoriesCollection } from "core/api/categories-collection.api";
import { CheckBoxConfigValue } from "common/components";

export function* productsSaga() {
  yield takeLatest([actions.FETCHED_PRODUCTS_TRIGGER], fetchProductsData);
  yield takeLatest([actions.FETCHED_PRODUCTS_TRIGGER], fetchCategoriesData);
}

export function* fetchProductsData() {
  console.log("fetch");
  const products: ProductEntityApi[] = yield call(getProductsCollection);
  yield put(
    fetchedProductsAction(mapFromAToBCollection(mapFromApiToVm, products))
  );
}

export function* fetchCategoriesData() {
  const suppliersLookup: CheckBoxConfigValue[] = yield call(
    getCategoriesCollection
  );
  yield put(
    fetchedCategoriesAction(
      mapFromAToBCollection(mapToCheckBoxValue, suppliersLookup)
    )
  );
}
