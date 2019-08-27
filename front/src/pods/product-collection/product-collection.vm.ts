export interface ProductEntityVm {
  category: string;
  productIdentifier : string;
  productName : string;
  productDescription: string;
  sizes: ProductEntitySizeVm[];
  comfortLevel: number;
  picture: string;
}

export interface ProductEntitySizeVm {
  size: string;
  price: string;
}