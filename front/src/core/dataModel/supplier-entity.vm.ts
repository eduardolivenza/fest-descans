
import { FieldValidationResult } from "lc-form-validation";
export interface SupplierVm {
    id: string;
    companyName: string;
    country: string;
  }

  export interface SupplierFormErrors {
    companyName: FieldValidationResult;
    country: FieldValidationResult;
    
  }
  
  export const createDefaultSupplierFormErrors = (): SupplierFormErrors => ({
    companyName: new FieldValidationResult(),
    country: new FieldValidationResult(),
  });

  export const createDefaultSupplier = (): SupplierVm => ({
    id: "",
    companyName: "string",
    country: "string",
  });