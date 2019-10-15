import Axios from "axios";
import {baseApiUrl} from 'core';
import {SupplierEntityApi} from 'core/apiModel/supplier-entity.api';


  const getSupplierUrl = `${baseApiUrl}/suppliers`;
  
  // TODO: Just only managing the "happy path", adding error handling here or upper level 
  // would be a good idea
  export const getSupplier = (id) : Promise<SupplierEntityApi> => { 
    const url = `${getSupplierUrl}/${id}`; 
    const promise = new Promise<SupplierEntityApi>((resolve, reject) => 
      Axios.get<SupplierEntityApi>(
        url).then((response) => resolve(response.data)
    ));
  
    return promise;
  }
  