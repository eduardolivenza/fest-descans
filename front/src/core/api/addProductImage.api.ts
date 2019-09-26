import axios from 'axios'
import { baseApiUrl } from 'core'

const backendImages = `${baseApiUrl}/products/images/`;

export const postImageToProduct = async (productId: string, file: File) => {

    const formData = new FormData();
    const url = backendImages + productId;
    formData.append('file', file);
    try {
        const res = await axios.post(url, formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
      } catch (err) {
        if (err.response.status === 500) {
          console.log("There was a problem with the server");
        } else {
          console.log(err.response.data.msg);
        }
      }

}