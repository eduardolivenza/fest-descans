import { FieldValidationResult } from "lc-form-validation";

export interface LoginFormErrors {
  email: FieldValidationResult;
  password: FieldValidationResult;
}

export const createDefaultLoginFormErrors = (): LoginFormErrors => ({
  email: new FieldValidationResult(),
  password: new FieldValidationResult()
});
