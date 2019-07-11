import { FieldValidationResult } from "lc-form-validation";

export interface RegisterEntityVm {
    email: string;
    firstName: string;
    lastName: string;
    password: string;
    passwordConfirm: string;
  }
  
  export const createRegisterEntity = (): RegisterEntityVm => ({
    email: "",
    firstName: "",
    lastName: "",
    password: "",
    passwordConfirm: "",
  });

  export interface RegisterFormErrors {
    email: FieldValidationResult;
    password: FieldValidationResult;
    passwordConfirm: FieldValidationResult;
  }
  
  export const createDefaultRegisterFormErrors = (): RegisterFormErrors => ({
    email: new FieldValidationResult(),
    password: new FieldValidationResult(),
    passwordConfirm: new FieldValidationResult()
  });