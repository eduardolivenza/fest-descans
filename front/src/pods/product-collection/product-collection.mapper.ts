import {ProductEntityApi} from './product-collection.api';
import {ProductEntityVm} from './product-collection.vm';
import {basePicturesUrl} from 'core';

export const mapFromApiToVm = (apiEntity : ProductEntityApi) : ProductEntityVm => ({
  category : apiEntity.category,
  productIdentifier : apiEntity.productIdentifier,
  productDescription : apiEntity.productDescription,
  comfortLevel: 3,
  picture : `${basePicturesUrl}` + "/images/" + apiEntity.productIdentifier,
  sizes: apiEntity.sizes,
});



