import { VALIDATION_TYPE, validateValueMatchValidator, CustomParams } from './string-match.validator';
import { FieldValidationResult } from "lc-form-validation";

const customParams : CustomParams = {
  stringToMatch: 'No city selected'
};
describe('validateStringMatch test', () => {
  describe('no match => ', () => {
    it('should invalidate when values do not match', () => {
  
      
      const result = validateValueMatchValidator('yes', '', customParams);

      // Assert
      expect(result).toEqual({
        errorMessage: '',
        key: '',
        succeeded: true,
        type: VALIDATION_TYPE,
      } as FieldValidationResult);
    })
  });

  describe('values match => ', () => {
    it('should validate when values do match', () => {
  
      const result = validateValueMatchValidator('No city selected', '', customParams);

      // Assert
      expect(result).toEqual({
        errorMessage: 'Invalid selection',
        key: '',
        succeeded: false,
        type: VALIDATION_TYPE,
      } as FieldValidationResult);
    })
  });

  describe('values match => ', () => {
    it('should invalidate when both values are undefined', () => {
  
      const result = validateValueMatchValidator(undefined, '', undefined);

      // Assert
      expect(result).toEqual({
        errorMessage: 'Invalid selection',
        key: '',
        succeeded: false,
        type: VALIDATION_TYPE,
      } as FieldValidationResult);
    })
  });

  describe('values match => ', () => {
    it('should invalidate when both values are empty', () => {
  
      const emptyCustomParams : CustomParams = {
        stringToMatch: ''
      }
      const result = validateValueMatchValidator('', '', emptyCustomParams);

      // Assert
      expect(result).toEqual({
        errorMessage: 'Invalid selection',
        key: '',
        succeeded: false,
        type: VALIDATION_TYPE,
      } as FieldValidationResult);
    })
  });
});