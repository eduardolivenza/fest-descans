import Axios, { AxiosResponse } from "axios";
import {baseApiUrl} from 'core'
import { RegisterEntityVm } from "pods/register/register.vm"

const backendUsers = `${baseApiUrl}/users`;

export const registerNewUser = (registerData: RegisterEntityVm, token: string) : Promise<AxiosResponse> => {  
    const promise = new Promise<AxiosResponse>((resolve, reject) => 
      Axios.post(backendUsers, registerData, { headers: {"Authorization" : `Bearer ${token}`} }).then((response) => resolve(response)
    ));  
    return promise;
}