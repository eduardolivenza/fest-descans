import {
  createFormValidation,
  ValidationConstraints,
  Validators
} from "lc-form-validation";

const SupplierEditFormValidationConstraints: ValidationConstraints = {
  fields: {
    companyName: [{ validator: Validators.required }],
  }
};

export const SupplierEditFormValidation = createFormValidation(
  SupplierEditFormValidationConstraints
);
