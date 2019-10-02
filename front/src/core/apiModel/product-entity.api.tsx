import {SupplierApi} from "./supplier.api";

export interface ProductEntityApi {
    internalIdentifier: string;
    category: CategoryEntityApi;
    productIdentifier: string;
    productName: string;
    productDescription: string;
    comfortLevel: number;
    images: string[];
    supplier: SupplierApi;
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