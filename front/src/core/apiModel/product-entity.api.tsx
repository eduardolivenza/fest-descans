import {SupplierEntityApi} from "./supplier-entity.api";

export interface ProductEntityApi {
    internalIdentifier: string;
    category: CategoryEntityApi;
    productIdentifier: string;
    productName: string;
    productDescription: string;
    comfortLevel: number;
    images: string[];
    supplier: SupplierEntityApi;
    sizes: ProductSizeEntityApi[];
  }

  export interface CategoryEntityApi {
    description : string;
    value: string;
  }

  export interface ProductSizeEntityApi {
    size: string;
    price : string;
  }