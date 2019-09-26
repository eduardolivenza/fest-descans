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
  thumbnail : apiEntity.images.length > 0 ? formatPicture("thumbnail", apiEntity.images[0]) : formatPicture("thumbnail", "defaultPic.jpg"),
  pictures: apiEntity.images.length > 0 ? formatPictures(apiEntity.images): [formatPicture("thumbnail", "defaultPic.jpg")],
  sizes: apiEntity.sizes,
  supplier: mapSupplier(apiEntity.supplier),
});

const formatPicture = (type: string, filename: string): string =>{
  return (`${basePicturesUrl}` + "/image/"+ type+"/" + filename);
}

const formatPictures = ( filenames: string[]): string[] => {
  let pictures: string[] = [];
  filenames.map( filename => {
      pictures.push(formatPicture("poster", filename));
    } 
  )
  return pictures;
}




