import { BaseAction } from "store/base-actions";
import { ProductEntityVm, ProductEntitySizeVm } from "core";
import { actions } from "pods/product-collection/constants";
import { CheckBoxConfigValue } from "common/components";

export const fetchedInitialDataAction = (): BaseAction => ({
  type: actions.FETCHED_DATA_TRIGGER
});

export const fetchedProductsAction = (payload:  ProductEntityVm[]): BaseAction => ({
  type: actions.FETCHED_PRODUCTS,
  payload
});

export const fetchedCategoriesAction = (payload:  CheckBoxConfigValue[]): BaseAction => ({
  type: actions.FETCHED_CATEGORIES,
  payload
});

export const handleChangeFilterTextAction = (payload: string): BaseAction => ({
  type: actions.FILTER_TEXT,
  payload
});

export const handleProductTypesFilterAction = (name: string, value: boolean): BaseAction => ({
  type: actions.FILTER_PRODUCTS_TYPES,
  payload: {
    name,
    value,
  },
});

export const handleChangePriceFilter = (payload: number[]): BaseAction => ({
  type: actions.FILTER_PRICES,
  payload
});
export const handleChangeComfortFilterAction = (name: string, value: boolean): BaseAction => ({
  type: actions.FILTER_COMFORT,
  payload: {
    name,
    value,
  },
});
export const removeProductAction = (payload: string): BaseAction => ({
  type: actions.REMOVE_PRODUCT,
  payload
});

export const addToCartAction = (product: ProductEntityVm, selectedSize: ProductEntitySizeVm): BaseAction => ({
  type: actions.ADD_TO_CART,
  payload: {
    product,
    selectedSize,
  },
});
