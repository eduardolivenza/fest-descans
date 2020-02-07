import { ProductEntityVm, createDefaultProduct, ProductCartEntityVm } from "core";
import { CheckBoxConfigValue } from "common/components";

export const initState: modelState = {
    productsCollection: [createDefaultProduct()],
    productsCollectionFiltered: [createDefaultProduct()],
    comfortLevelCheckboxes: [
        {
          name: "4",
          label: "Very hard",
          value: true
        },
        {
          name: "3",
          label: "Hard",
          value: true
        },
        {
          name: "2",
          label: "Medium",
          value: true
        },
        {
          name: "1",
          label: "Soft",
          value: true
        },
        {
          name: "0",
          label: "Very soft",
          value: true
        }
      ],
    productTypesFilterItems: [],
    priceFilter: [0, 100],
    maxPrice: 100,
    textFilterValue: "",
    addedItems:[],
}


export interface modelState {
    productsCollection: ProductEntityVm[],
    productsCollectionFiltered: ProductEntityVm[],
    comfortLevelCheckboxes: CheckBoxConfigValue[],
    productTypesFilterItems: CheckBoxConfigValue[],
    priceFilter: number[],
    maxPrice: number,
    textFilterValue: string,
    //new for cart
    addedItems: ProductCartEntityVm[],
}