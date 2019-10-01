import axios,{ AxiosResponse } from 'axios'
import { baseApiUrl } from 'core'

const backendImages = `${baseApiUrl}/products/images/`;

export const postImageToProduct = (productId: string, file: File) => {

  const formData = new FormData();
  const url = backendImages + productId;
  formData.append('file', file);

  const promise = new Promise<AxiosResponse>((resolve, reject) =>
    axios.post(url, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    }).then((response) => {
      resolve(response);
    }
    ).catch(error => reject(error)));
  return promise;

}