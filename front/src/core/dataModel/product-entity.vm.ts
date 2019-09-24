import { SupplierVm } from "core/dataModel/supplier-entity.vm";
import { FieldValidationResult } from "lc-form-validation";

export interface ProductEntityVm {
  category: string;
  productIdentifier: string;
  productName: string;
  productDescription: string;
  sizes: ProductEntitySizeVm[];
  supplier: SupplierVm;
  comfortLevel: number;
  picture: string;
  thumbnail: string;
}

export interface ProductEntitySizeVm {
  size: string;
  price: string;
}


export const createDefaultProduct = (): ProductEntityVm => ({
  category: "string",
  productIdentifier: "prod01",
  productName: "prodname",
  productDescription: "desc",
  sizes: [],
  supplier: null,
  comfortLevel: 3,
  picture: "",
  thumbnail: "",
});

export interface ProductFormErrors {
  productName: FieldValidationResult;
  // city: FieldValidationResult;
  productDescription: FieldValidationResult;
}

export const createDefaultProductFormErrors = (): ProductFormErrors => ({
  productName: new FieldValidationResult(),
  // city: new FieldValidationResult(),
  productDescription: new FieldValidationResult()
});