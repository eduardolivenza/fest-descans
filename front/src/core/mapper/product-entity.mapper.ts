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
  picture :   `${basePicturesUrl}` + "/image/poster/" + apiEntity.productIdentifier + ".jpg",
  thumbnail : `${basePicturesUrl}` + "/image/thumbnail/" + apiEntity.productIdentifier + ".jpg",
  pictures: formatPictures(apiEntity.images),
  sizes: apiEntity.sizes,
  supplier: mapSupplier(apiEntity.supplier),
});

const formatPictures = ( filenames: string[]): string[] => {
  let pictures: string[] = [];
  filenames.map( filename => {
      pictures.push(`${basePicturesUrl}` + "/image/poster/" + filename);
    }
  )
  return pictures;
}




