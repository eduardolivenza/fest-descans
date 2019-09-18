import Axios, { AxiosResponse } from "axios";
import { baseApiUrl } from 'core'
import { LoginEntityVm } from "core/dataModel/login-entity.vm";
import {mapCredentials} from "core/mapper/login-entity.mapper";

const backendUsers = `${baseApiUrl}/users/authenticate`;

export const validateCredentials = (credentials: LoginEntityVm): Promise<AxiosResponse> => {


  const promise = new Promise<AxiosResponse>((resolve, reject) =>
    Axios.post(backendUsers, mapCredentials(credentials)).then((response) => {
      resolve(response);
    }
  ).catch(error=> reject(error)));
  return promise;
}
