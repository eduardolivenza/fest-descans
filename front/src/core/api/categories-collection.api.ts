import Axios from "axios";
import {baseApiUrl} from 'core';
import {CategoryEntityApi} from 'core/apiModel/product-entity.api';

const getProductsUrl = `${baseApiUrl}/categories`;

export const getCategoriesCollection = () : Promise<CategoryEntityApi[]> => {  
  const promise = new Promise<CategoryEntityApi[]>((resolve, reject) => 
    Axios.get<CategoryEntityApi[]>(getProductsUrl).then((response) => resolve(response.data)
  ));
  return promise;
}

