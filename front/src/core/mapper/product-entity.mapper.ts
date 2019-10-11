import {ProductEntityApi} from 'core/apiModel/product-entity.api'
import {ProductEntityVm} from '../dataModel/product-entity.vm';
import {basePicturesUrl} from 'core';
import {mapSupplierToVm, mapSupplierToApi} from 'core/mapper/supplier-entity.mapper';

export const mapFromApiToVm = (apiEntity : ProductEntityApi) : ProductEntityVm => ({
  internalIdentifier: apiEntity.internalIdentifier,
  category : apiEntity.category,
  productName: apiEntity.productName, 
  productDescription : apiEntity.productDescription,
  comfortLevel: apiEntity.comfortLevel,
  thumbnail : apiEntity.images.length > 0 ? formatPicture("thumbnail", apiEntity.images[0]) : formatPicture("thumbnail", "defaultPic.jpg"),
  pictures: apiEntity.images.length > 0 ? formatPictures(apiEntity.images): [formatPicture("thumbnail", "defaultPic.jpg")],
  sizes: apiEntity.sizes,
  supplier: mapSupplierToVm(apiEntity.supplier),
});

export const mapFromVmToApi = (vmEntity : ProductEntityVm) : ProductEntityApi => ({
  internalIdentifier: vmEntity.internalIdentifier,
  category : vmEntity.category,
  productName: vmEntity.productName, 
  productDescription : vmEntity.productDescription,
  comfortLevel: vmEntity.comfortLevel,
  images: [],
  sizes: vmEntity.sizes,
  supplier: mapSupplierToApi(vmEntity.supplier),
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




