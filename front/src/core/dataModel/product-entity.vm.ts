import { SupplierVm } from "core/dataModel/supplier-entity.vm";
import { FieldValidationResult } from "lc-form-validation";

export interface ProductEntityVm {
  internalIdentifier: string;
  category: CategoryEntityVm;
  productName: string;
  productDescription: string;
  sizes: ProductEntitySizeVm[];
  supplier: SupplierVm;
  comfortLevel: number;
  pictures: string[];
  thumbnail: string;
}

export interface ProductEntitySizeVm {
  size: string;
  price: string;
}

export interface CategoryEntityVm {
  value: string;
  description : string;
}

export const createDefaultProduct = (): ProductEntityVm => ({
  internalIdentifier: "InternalUUID",
  category: {
    value: "",
    description: ""
  },
  productName: "prodname",
  productDescription: "description",
  sizes: [{
    size: "90x190",
    price: "100",
  }],
  supplier: {
    companyName: "",
    id: "",
    country: ""
  },
  comfortLevel: 3,
  pictures: [],
  thumbnail: "",
});

export interface ProductFormErrors {
  productName: FieldValidationResult;
  category: FieldValidationResult;
  productDescription: FieldValidationResult;
}

export const createDefaultProductFormErrors = (): ProductFormErrors => ({
  productName: new FieldValidationResult(),
  category: new FieldValidationResult(),
  productDescription: new FieldValidationResult()
});