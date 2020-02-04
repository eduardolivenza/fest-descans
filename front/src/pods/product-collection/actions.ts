import { BaseAction } from "store/base-actions";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import { actions } from "pods/product-collection/constants";
import { CheckBoxConfigValue } from "common/components";

export const fetchedProductsTriggerAction = (): BaseAction => ({
  type: actions.FETCHED_PRODUCTS_TRIGGER
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