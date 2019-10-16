import {
  createFormValidation,
  ValidationConstraints,
  Validators
} from "lc-form-validation";

const SupplierEditFormValidationConstraints: ValidationConstraints = {
  fields: {
    id: [{ validator: Validators.required }],
    companyName: [{ validator: Validators.required }],
    country: [{ validator: Validators.required }],
  }
};

export const SupplierEditFormValidation = createFormValidation(
  SupplierEditFormValidationConstraints
);
