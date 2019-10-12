import { SupplierEntityApi } from 'core/apiModel/supplier-entity.api';
import {SupplierVm} from "core/dataModel/supplier-entity.vm";
import { LookupEntity } from 'core';

export const mapSupplierToVm = (apiEntity : SupplierEntityApi) : SupplierVm => ({
    id: apiEntity.companyId,
    companyName: apiEntity.companyName,
    country: apiEntity.country,
});

export const mapSupplierToApi = (vmEntity : SupplierVm) : SupplierEntityApi => ({
    companyId: vmEntity.id,
    companyName: vmEntity.companyName,
    country: vmEntity.country,
});

export const mapToLookup= (apiEntity : SupplierEntityApi) : LookupEntity => ({
    value: apiEntity.companyId,
    description: apiEntity.companyName,
});