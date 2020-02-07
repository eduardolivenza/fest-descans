import { modelState, initState } from "./modelState";
import { actions } from "pods/product-collection/constants";
import { BaseAction, StringBoolean } from "store/base-actions";
import { CheckBoxConfigValue } from "common/components";
import { ProductCartEntityVm } from "core";

function handleFetchedProducts(state: modelState, payload) {
  let maxPriceLocal = state.maxPrice;
  payload.map(item => {
    item.sizes.map(avSize => {
      if (parseInt(avSize.price) >= maxPriceLocal) {
        maxPriceLocal = parseInt(avSize.price);
      }
    });
  });
  return {
    ...state,
    productsCollection: payload,
    productsCollectionFiltered: payload,
    maxPrice: maxPriceLocal,
    priceFilter: [0, maxPriceLocal]
  };
}

function handleCategories(state: modelState, payload) {
  return {
    ...state,
    productTypesFilterItems: payload
  };
}

function filterTextProducts(state: modelState, text: string) {
  const newState = {
    ...state,
    textFilterValue: text
  };
  return applyFilter(newState);
}

function filterPriceProducts(state: modelState, values: number[]) {
  const newState = {
    ...state,
    priceFilter: values
  };
  return applyFilter(newState);
}

function filterProductTypes(state: modelState, values: StringBoolean) {
  let itemIndex: number = state.productTypesFilterItems.findIndex(
    item => item.name === values.name
  );
  let newProductTypes: CheckBoxConfigValue[] = [
    ...state.productTypesFilterItems
  ];
  newProductTypes[itemIndex].value = values.value;
  const newState = {
    ...state,
    productTypesFilterItems: newProductTypes
  };
  return applyFilter(newState);
}

function filterComfortLevel(state: modelState, values: StringBoolean) {
  let itemIndex: number = state.comfortLevelCheckboxes.findIndex(
    item => item.name === values.name
  );
  let newComfortLevelCheckboxes: CheckBoxConfigValue[] = [
    ...state.comfortLevelCheckboxes
  ];
  newComfortLevelCheckboxes[itemIndex].value = values.value;
  const newState = {
    ...state,
    comfortLevelCheckboxes: newComfortLevelCheckboxes
  };
  return applyFilter(newState);
}

const applyFilter = (state: modelState): modelState => {
  let newArray = state.productsCollection;
  Object.keys(state.comfortLevelCheckboxes).forEach(position => {
    if (state.comfortLevelCheckboxes[position].value === false) {
      newArray = newArray.filter(
        result =>
          result.comfortLevel !=
          parseInt(state.comfortLevelCheckboxes[position].name) + 1
      );
    }
  });
  Object.keys(state.productTypesFilterItems).forEach(position => {
    if (state.productTypesFilterItems[position].value === false) {
      newArray = newArray.filter(
        result =>
          result.category.value != state.productTypesFilterItems[position].name
      );
    }
  });
  newArray = newArray.filter(x =>
    x.sizes.some(
      y =>
        parseInt(y.price) >= state.priceFilter[0] &&
        parseInt(y.price) <= state.priceFilter[1]
    )
  );
  newArray = newArray.filter(x =>
    x.productName.toLowerCase().includes(state.textFilterValue.toLowerCase())
  );
  return {
    ...state,
    productsCollectionFiltered: newArray
  };
};

function addToCart(state: modelState, productAdded: ProductCartEntityVm) {
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

export const productsCollectionReducer = (
  state: modelState = initState,
  action: BaseAction
) => {
  switch (action.type) {
    case actions.FETCHED_PRODUCTS:
      return handleFetchedProducts(state, action.payload);
    case actions.FETCHED_CATEGORIES:
      return handleCategories(state, action.payload);
    case actions.FILTER_TEXT:
      return filterTextProducts(state, action.payload);
    case actions.FILTER_PRICES:
      return filterPriceProducts(state, action.payload);
    case actions.FILTER_PRODUCTS_TYPES:
      return filterProductTypes(state, action.payload);
    case actions.FILTER_COMFORT:
      return filterComfortLevel(state, action.payload);
    case actions.ADD_TO_CART:
      return addToCart(state, action.payload);
    default:
      return state;
  }
};
