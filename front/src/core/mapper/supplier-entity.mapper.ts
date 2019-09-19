import { SupplierApi } from 'core/apiModel/supplier.api';
import {SupplierVm} from "core/dataModel/supplier-entity.vm";

export const mapSupplier = (apiEntity : SupplierApi) : SupplierVm => ({
    id: apiEntity.companyId,
    companyName: apiEntity.companyName,
    country: apiEntity.country,
});