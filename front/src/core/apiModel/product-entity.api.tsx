import {SupplierApi} from "./supplier.api";

export interface ProductEntityApi {
    category: string;
    productIdentifier: string;
    productName: string;
    productDescription: string;
    comfortLevel: number;
    images: string[];
    supplier: SupplierApi;
    sizes: ProductSizeEntityApi[];
  }
    
  export interface ProductSizeEntityApi {
    size: string;
    price : string;
  }