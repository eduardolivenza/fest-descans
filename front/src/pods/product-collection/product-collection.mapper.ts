import {ProductEntityApi} from './product-collection.api';
import {ProductEntityVm} from './product-collection.vm';
import {basePicturesUrl} from 'core';

export const mapFromApiToVm = (apiEntity : ProductEntityApi) : ProductEntityVm => ({
  category : apiEntity.category,
  productIdentifier : apiEntity.productIdentifier,
  productDescription : apiEntity.productDescription,
  picture : `${basePicturesUrl}` + "/" + apiEntity.productIdentifier,
  sizes: apiEntity.sizes,
});

//picture : `${basePicturesUrl}${apiEntity.thumbNailUrl}`,


