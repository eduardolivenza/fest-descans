import Axios from "axios";
import {baseApiUrl} from 'core';
import { SupplierEntityApi } from "core/apiModel/supplier-entity.api";

const getSuppliersUrl = `${baseApiUrl}/suppliers`;

export const getSuppliersCollection = () : Promise<SupplierEntityApi[]> => {  
  
  const promise = new Promise<SupplierEntityApi[]>((resolve, reject) => 
    Axios.get<SupplierEntityApi[]>(getSuppliersUrl).then((response) => resolve(response.data)
  ));
  return promise;
}

