import {ProductEntityApi} from './product-collection.api';
import {ProductEntityVm} from './product-collection.vm';
import {basePicturesUrl} from 'core';

export const mapFromApiToVm = (apiEntity : ProductEntityApi) : ProductEntityVm => ({
  email : apiEntity.email,
  picture : `${basePicturesUrl}`,
  firstName : apiEntity.firstName,
  lastName : apiEntity.lastName,
});

 // picture : `${basePicturesUrl}${apiEntity.thumbNailUrl}`,


