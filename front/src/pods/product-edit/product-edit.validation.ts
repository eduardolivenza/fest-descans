import {
    createFormValidation,
    ValidationConstraints,
    Validators
} from "lc-form-validation";
import { validateValueMatchValidator } from "common/validators";

const ProductEditFormValidationConstraints: ValidationConstraints = {
    fields: {
        productName: [{ validator: Validators.required }],
       /* city: [{ 
                 validator: validateValueMatchValidator, 
                 customParams: {stringToMatch: 'No city selected'} 
              }],*/
        productDescription: [
            { validator: Validators.required },
            {
                validator: Validators.maxLength,
                customParams: { length: 250 }
            },
            {
                validator: Validators.minLength,
                customParams: { length: 10 }
            }]

    }
};

export const ProductEditFormValidation = createFormValidation(
    ProductEditFormValidationConstraints
);
