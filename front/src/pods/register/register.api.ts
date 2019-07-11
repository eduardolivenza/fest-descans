import Axios, { AxiosResponse } from "axios";
import {baseApiUrl} from 'core'
import { RegisterEntityVm } from "./register.vm"

const backendProducts = `${baseApiUrl}/products`;

export const registerNewProduct = (registerData: RegisterEntityVm) : Promise<AxiosResponse> => {  
    const promise = new Promise<AxiosResponse>((resolve, reject) => 
      Axios.post(backendProducts, registerData).then((response) => resolve(response)
    ));  
    return promise;
}