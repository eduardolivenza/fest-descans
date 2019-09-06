import {
    createFormValidation,
    ValidationConstraints,
    Validators
} from "lc-form-validation";
import { validateValueMatchValidator } from "common/validators";

const HotelEditFormValidationConstraints: ValidationConstraints = {
    fields: {
        name: [{ validator: Validators.required }],
        city: [{ 
                 validator: validateValueMatchValidator, 
                 customParams: {stringToMatch: 'No city selected'} 
              }],
        description: [
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

export const HotelEditFormValidation = createFormValidation(
    HotelEditFormValidationConstraints
);
