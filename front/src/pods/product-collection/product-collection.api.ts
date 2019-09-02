import Axios from "axios";
import {baseApiUrl} from 'core'

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

const getProductsUrl = `${baseApiUrl}/products`;


// TODO: Just only managing the "happy path", adding error handling here or upper level 
// would be a good idea
export const getProductsCollection = () : Promise<ProductEntityApi[]> => {  
  const promise = new Promise<ProductEntityApi[]>((resolve, reject) => 
    Axios.get<ProductEntityApi[]>(getProductsUrl).then((response) => resolve(response.data)
  ));

  return promise;
}

