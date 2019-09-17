import Axios, { AxiosResponse } from "axios";
import { baseApiUrl } from 'core'
import { LoginEntityApi } from  "core/apiModel/login-entity.api";

const backendUsers = `${baseApiUrl}/users/authenticate`;

export const validateCredentials = (email: string, password: string): Promise<AxiosResponse> => {

  const mapCredentials = (email: string, password: string): LoginEntityApi => (
  { 
    email: email,
    password: password
  });

  const promise = new Promise<AxiosResponse>((resolve, reject) =>
    Axios.post(backendUsers, mapCredentials(email, password)).then((response) => {
      resolve(response);
    }
  ).catch(error=> reject(error)));
  return promise;
}
