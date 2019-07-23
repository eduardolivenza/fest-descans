export interface ProductEntityVm {
  category: string;
  productIdentifier : string;
  productDescription: string;
  sizes: ProductEntitySizeVm[];
  picture: string;
}

export interface ProductEntitySizeVm {
  size: string;
  price: string;
}