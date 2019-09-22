import {ProductEntityApi} from 'core/apiModel/product-entity.api'
import {ProductEntityVm} from '../dataModel/product-entity.vm';
import {basePicturesUrl} from 'core';
import {mapSupplier} from 'core/mapper/supplier-entity.mapper';

export const mapFromApiToVm = (apiEntity : ProductEntityApi) : ProductEntityVm => ({
  category : apiEntity.category,
  productIdentifier : apiEntity.productIdentifier,
  productName: apiEntity.productName, 
  productDescription : apiEntity.productDescription,
  comfortLevel: apiEntity.comfortLevel,
  picture : `${basePicturesUrl}` + "/images/" + apiEntity.productIdentifier,
  sizes: apiEntity.sizes,
  supplier: mapSupplier(apiEntity.supplier),
});




