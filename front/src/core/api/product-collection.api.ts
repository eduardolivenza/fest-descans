import Axios from "axios";
import {baseApiUrl} from 'core';
import {ProductEntityApi} from 'core/apiModel/product-entity.api';

const getProductsUrl = `${baseApiUrl}/products`;


// TODO: Just only managing the "happy path", adding error handling here or upper level 
// would be a good idea
export const getProductsCollection = () : Promise<ProductEntityApi[]> => {  
  const promise = new Promise<ProductEntityApi[]>((resolve, reject) => 
    Axios.get<ProductEntityApi[]>(getProductsUrl).then((response) => resolve(response.data)
  ));

  return promise;
}

