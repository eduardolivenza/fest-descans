export interface ProductEntityVm {
  category: string;
  productIdentifier : string;
  productName : string;
  productDescription: string;
  sizes: ProductEntitySizeVm[];
  supplier: SupplierVm;
  comfortLevel: number;
  picture: string;
}

export interface ProductEntitySizeVm {
  size: string;
  price: string;
}

export interface SupplierVm {
  companyId: string;
  companyName: string;
  country: string;
}