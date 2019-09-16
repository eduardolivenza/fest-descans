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

export const createDefaultProduct = (): ProductEntityVm => ({
  category: "string",
  productIdentifier : "prod01",
  productName : "prodname",
  productDescription: "desc",
  sizes: [],
  supplier: null,
  comfortLevel: 3,
  picture: "",
});