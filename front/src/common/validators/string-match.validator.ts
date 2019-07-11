import { FieldValidationResult } from "lc-form-validation";

const defaultInvalidMessage = 'Invalid selection';
export const VALIDATION_TYPE = 'VALUE_MATCH';

export interface CustomParams {
  stringToMatch : string;
}

export const validateValueMatchValidator = (value: any, vm: any, customParams: CustomParams) : FieldValidationResult =>
{
  const isInformed : boolean = isValueAndParamsInformed(value, customParams);
  let validationPassed : boolean = true;

  if (isInformed) {
    validationPassed = executeValidation(value, customParams.stringToMatch);
  }

  return buildFieldValidationResult(!validationPassed);
}


export const isValueAndParamsInformed = (value: any, customParams: CustomParams) : boolean => {
  return !(value === void(0) || customParams.stringToMatch === void(0) || value === '' || customParams.stringToMatch === '')
}

export const executeValidation = (value: string, stringToMatch: string) : boolean => {
  return value === stringToMatch;
}

export const buildFieldValidationResult = (validationPassed : boolean) : FieldValidationResult => {
  const fieldValidationResult: FieldValidationResult = new FieldValidationResult();
  fieldValidationResult.errorMessage = validationPassed ? '' : defaultInvalidMessage;
  fieldValidationResult.type = VALIDATION_TYPE;
  fieldValidationResult.succeeded = validationPassed;

  return fieldValidationResult;
}