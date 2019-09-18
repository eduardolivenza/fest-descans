import {
  createFormValidation,
  ValidationConstraints,
  Validators
} from "lc-form-validation";

const loginFormValidationConstraints: ValidationConstraints = {
  fields: {
    email: [{ validator: Validators.required }],
    password: [{ validator: Validators.required }],
  }
};

export const loginFormValidation = createFormValidation(
  loginFormValidationConstraints
);
