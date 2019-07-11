import {
  createFormValidation,
  ValidationConstraints,
  Validators,
  FieldValidationResult
} from "lc-form-validation";

function passwordAndConfirmPasswordValidationHandler(value: any, vm: any): FieldValidationResult {
  const passwordAndConfirmPasswordAreEqual = vm.password === value;
  const errorInfo = (passwordAndConfirmPasswordAreEqual) ? '' : 'Passwords do not match';

  const fieldValidationResult: FieldValidationResult = new FieldValidationResult();
  fieldValidationResult.type = 'PASSWORD_MATCH';
  fieldValidationResult.succeeded = passwordAndConfirmPasswordAreEqual;
  fieldValidationResult.errorMessage = errorInfo;

  return fieldValidationResult;
}

const registerFormValidationConstraints: ValidationConstraints = {
  fields: {
    email: [{ validator: Validators.required }],
    password: [
      { validator: Validators.required },
      {
        validator: Validators.minLength,
        customParams: { length: 4 },
      }
    ],
    passwordConfirm: [
      { validator: Validators.required }, 
      { validator: passwordAndConfirmPasswordValidationHandler },
    ],
  }
};

export const registerFormValidation = createFormValidation(
  registerFormValidationConstraints
);
