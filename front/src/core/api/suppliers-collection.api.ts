import Axios from "axios";
import {baseApiUrl} from 'core';
import { SupplierEntityApi } from "core/apiModel/supplier-entity.api";

const getSuppliersUrl = `${baseApiUrl}/suppliers`;

export const getSuppliersCollection = (token: string) : Promise<SupplierEntityApi[]> => {  
  
  const promise = new Promise<SupplierEntityApi[]>((resolve, reject) => 
    Axios.get<SupplierEntityApi[]>(getSuppliersUrl, { headers: {"Authorization" : `Bearer ${token}`} }).then((response) => resolve(response.data)
  ));
  return promise;
}

