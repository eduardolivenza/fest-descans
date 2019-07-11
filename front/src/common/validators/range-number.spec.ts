import { validateRangeNumber, VALIDATION_TYPE } from './range-number';
import { FieldValidationResult } from 'lc-form-validation';

describe('validateRangeNumber test', () => {
    describe('rangeNumber =>', () => {
        it('should invalidate when value is higher than 5', () => {
            const viewModel = { rating: 7 };
            // Act
            const result = validateRangeNumber(viewModel.rating, viewModel, { minimum: 3, maximum: 5 });
            // Assert
            expect(result).toEqual({
                errorMessage: 'Error. It has to be an integer between 3 and 5',
                key: '',
                succeeded: false,
                type: VALIDATION_TYPE,
            } as FieldValidationResult);
        });
        it('should invalidate when value is lower than 3', () => {
            const viewModel = { rating: 2 };
            // Act
            const result = validateRangeNumber(viewModel.rating, viewModel, { minimum: 3, maximum: 5 });
            // Assert
            expect(result).toEqual({
                errorMessage: 'Error. It has to be an integer between 3 and 5',
                key: '',
                succeeded: false,
                type: VALIDATION_TYPE,
            } as FieldValidationResult);
        });
        it('should invalidate when value is not an integer', () => {
            const viewModel = { rating: 4.6 };
            // Act
            const result = validateRangeNumber(viewModel.rating, viewModel, { minimum: 3, maximum: 5 });
            // Assert
            expect(result).toEqual({
                errorMessage: 'Error. It has to be an integer between 3 and 5',
                key: '',
                succeeded: false,
                type: VALIDATION_TYPE,
            } as FieldValidationResult);
        });
        it('should validate when value range is between 3 and 5 both included', () => {
            const viewModel = { rating: 4 };
            // Act
            const result = validateRangeNumber(viewModel.rating, viewModel, { minimum: 3, maximum: 5 });
            // Assert
            expect(result).toEqual({
                errorMessage: '',
                key: '',
                succeeded: true,
                type: VALIDATION_TYPE,
            } as FieldValidationResult);
        });
        it('should invalidate when range is not informed (null)', () => {
            expect(() => {
                validateRangeNumber(5, {}, null);
                }).toThrow();
        });
        it('should invalidate when range is not informed (undefined)', () => {
            expect(() => {
                validateRangeNumber(5, {}, undefined);
                }).toThrow();
        });
        it('should invalidate when minimum is not informed', () => {
            expect(() => {
                validateRangeNumber(5, {}, {minimum:null,maximum:5});
                }).toThrow();
        });
        it('should invalidate when maximum is not informed', () => {
            expect(() => {
                validateRangeNumber(5, {}, {minimum:3,maximum:null});
                }).toThrow();
        });
        it('should invalidate when maximum is lower than minimum value', () => {
            expect(() => {
                validateRangeNumber(5, {}, {minimum:5,maximum:3});
                }).toThrow();
        });
    });
})