import Axios, { AxiosResponse } from "axios";
import {baseApiUrl} from 'core'
import { RegisterEntityVm } from "./register.vm"

const backendUsers = `${baseApiUrl}/users`;

export const registerNewProduct = (registerData: RegisterEntityVm) : Promise<AxiosResponse> => {  
    const promise = new Promise<AxiosResponse>((resolve, reject) => 
      Axios.post(backendUsers, registerData).then((response) => resolve(response)
    ));  
    return promise;
}