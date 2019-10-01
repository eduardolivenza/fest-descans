import {SupplierApi} from "./supplier.api";

export interface ProductEntityApi {
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