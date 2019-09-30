import { SupplierApi } from 'core/apiModel/supplier.api';
import {SupplierVm} from "core/dataModel/supplier-entity.vm";

export const mapSupplierToVm = (apiEntity : SupplierApi) : SupplierVm => ({
    id: apiEntity.companyId,
    companyName: apiEntity.companyName,
    country: apiEntity.country,
});

export const mapSupplierToApi = (vmEntity : SupplierVm) : SupplierApi => ({
    companyId: vmEntity.id,
    companyName: vmEntity.companyName,
    country: vmEntity.country,
});