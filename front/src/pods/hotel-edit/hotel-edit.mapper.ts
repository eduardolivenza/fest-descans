import {HotelEditEntityApi} from './hotel-edit.api';
import {HotelEntityVm} from './hotel-edit.vm';
import {basePicturesUrl} from 'core';

export const mapFromApiToVm = (apiEntity : HotelEditEntityApi) : HotelEntityVm => ({
    id : apiEntity.id,
    picture : `${basePicturesUrl}${apiEntity.thumbNailUrl}`,
    name : apiEntity.name,
    description : apiEntity.shortDescription,
    rating : apiEntity.hotelRating,
    address : apiEntity.address1,
    city : apiEntity.city,
});