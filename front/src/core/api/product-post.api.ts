import Axios, { AxiosResponse }  from "axios";
import { baseApiUrl } from 'core';
import { ProductEntityVm } from 'core/dataModel/product-entity.vm';
import { mapFromVmToApi } from "core/mapper/product-entity.mapper";

  const postProductUrl = `${baseApiUrl}/products`;
  
  // TODO: Just only managing the "happy path", adding error handling here or upper level 
  // would be a good idea
  export const postNewProduct = ( product: ProductEntityVm) : Promise<AxiosResponse> => { 
    const url = `${postProductUrl}`; 
    const promise = new Promise<AxiosResponse>((resolve, reject) => 
      Axios.post<AxiosResponse>(
        url, mapFromVmToApi(product)).then((response) => resolve(response.data)
    ));
  
    return promise;
  }
  