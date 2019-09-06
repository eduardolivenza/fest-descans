export interface ProductEntityApi {
    category: string;
    productIdentifier: string;
    productName: string;
    productDescription: string;
    comfortLevel: number;
    picture: string;
    supplier: SupplierApi;
    sizes: ProductSizeEntityApi[];
  }
  
  export interface SupplierApi {
    companyId: string;
    companyName: string;
    country: string;
  }
  
  export interface ProductSizeEntityApi {
    size: string;
    price : string;
  }