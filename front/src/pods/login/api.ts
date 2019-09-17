import Axios, { AxiosResponse } from "axios";
import { baseApiUrl } from 'core'
import { LoginEntity } from  "./login.vm";

const backendUsers = `${baseApiUrl}/users/authenticate`;

export const validateCredentials = (credentials: LoginEntity): Promise<AxiosResponse> => {

  const promise = new Promise<AxiosResponse>((resolve, reject) =>
    Axios.post(backendUsers, credentials).then((response) => {
      resolve(response);
    }
  ).catch(error=> reject(error)));
  return promise;
}
