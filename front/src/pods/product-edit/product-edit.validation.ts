import {
  createFormValidation,
  ValidationConstraints,
  Validators
} from "lc-form-validation";
import { validateValueMatchValidator } from "common/validators";

const ProductEditFormValidationConstraints: ValidationConstraints = {
  fields: {
    productName: [{ validator: Validators.required }],
    category: [
      {
        validator: validateValueMatchValidator,
        customParams: { stringToMatch: "No category selected" }
      }
    ],
    productIdentifier: [
      { validator: Validators.required },
      {
        validator: Validators.minLength,
        customParams: { length: 6 }
      }
    ],
    productDescription: [
      { validator: Validators.required },
      {
        validator: Validators.maxLength,
        customParams: { length: 250 }
      },
      {
        validator: Validators.minLength,
        customParams: { length: 10 }
      }
    ]
  }
};

export const ProductEditFormValidation = createFormValidation(
  ProductEditFormValidationConstraints
);
