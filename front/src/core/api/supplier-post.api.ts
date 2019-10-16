import Axios, { AxiosResponse }  from "axios";
import { baseApiUrl } from 'core';
import { mapSupplierToApi } from "core/mapper/supplier-entity.mapper";
import { SupplierVm } from "core/dataModel/supplier-entity.vm";

  const postNewSupplierUrl = `${baseApiUrl}/suppliers`;
  
  export const postNewSupplier = ( product: SupplierVm) : Promise<AxiosResponse> => { 
    const url = `${postNewSupplierUrl}`; 
    const promise = new Promise<AxiosResponse>((resolve, reject) => 
      Axios.post<AxiosResponse>(
        url, mapSupplierToApi(product)).then((response) => resolve(response.data)
    ));
    return promise;
  }
  