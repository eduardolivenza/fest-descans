import {ProductEntityApi} from './product-collection.api';
import {ProductEntityVm} from './product-collection.vm';
import {basePicturesUrl} from 'core';

export const mapFromApiToVm = (apiEntity : ProductEntityApi) : ProductEntityVm => ({
  category : apiEntity.category,
  productIdentifier : apiEntity.productIdentifier,
  productName: apiEntity.productName, 
  productDescription : apiEntity.productDescription,
  comfortLevel: apiEntity.comfortLevel,
  picture : `${basePicturesUrl}` + "/images/" + apiEntity.productIdentifier,
  sizes: apiEntity.sizes,
});



