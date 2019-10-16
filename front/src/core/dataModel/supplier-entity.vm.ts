
import { FieldValidationResult } from "lc-form-validation";
export interface SupplierVm {
    id: string;
    companyName: string;
    country: string;
  }

  export interface SupplierFormErrors {
    id: FieldValidationResult;
    companyName: FieldValidationResult;
    country: FieldValidationResult;
    
  }
  
  export const createDefaultSupplierFormErrors = (): SupplierFormErrors => ({
    id: new FieldValidationResult(),
    companyName: new FieldValidationResult(),
    country: new FieldValidationResult(),
  });

  export const createDefaultSupplier = (): SupplierVm => ({
    id: "",
    companyName: "",
    country: "",
  });