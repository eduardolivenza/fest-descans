import { FieldValidationResult } from 'lc-form-validation';
import { ProductEditFormValidation } from './product-edit.validation';

describe('Product edit Validation tests', () => {
    describe('Field Validations =>', () => {
        it('should invalidate when productName does not exist', (done) => {

            // Arrange
            const viewModel = {
                productName: 'arts',
                category: 'BED',
                productDescription: 'productDescription'
            };

            // Act
            ProductEditFormValidation
                .validateField(viewModel, 'productName', "")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.errorMessage).toEqual('Please fill in this mandatory field.');
                    done();
                });

        });

        /*
        it('should invalidate when city does not exist', (done) => {

            // Arrange
            const viewModel = {
                productName: 'arts',
                city: 'BED',
                productDescription: 'productDescription'
            };

            // Act
            ProductEditFormValidation
                .validateField(viewModel, 'city', "")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.succeeded).toBeFalsy();
                    //expect(fieldValidationResult.errorMessage).toEqual('Please fill in this mandatory field.');
                    done();
                });

        });
        */
        it('should invalidate when productDescription does not exist', (done) => {

            // Arrange
            const viewModel = {
                productName: 'arts',
                city: 'BED',
                productDescription: 'productDescription'
            };

            // Act
            ProductEditFormValidation
                .validateField(viewModel, 'productDescription', "")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.errorMessage).toEqual('Please fill in this mandatory field.');
                    done();
                });

        });

        it('should validate when productName is correct', (done) => {

            // Arrange
            const viewModel = {
                productName: 'arts',
                category: 'BED',
                productDescription: 'productDescription'
            };

            // Act
            ProductEditFormValidation
                .validateField(viewModel, 'productName', "arts")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.succeeded).toBeTruthy();
                    done();
                });

        });

        /*
        it('should validate when city is correct', (done) => {

            // Arrange
            const viewModel = {
                productName: 'arts',
                city: 'BED',
                productDescription: 'productDescription'
            };

            // Act
            ProductEditFormValidation
                .validateField(viewModel, 'city', "Berlin")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.succeeded).toBeTruthy();
                    done();
                });

        });
        */
        it('should validate when productDescription is correct', (done) => {
            // Arrange
            const viewModel = {
                productName: 'arts',
                category: 'BED',
                productDescription: 'productDescription'
            };

            // Act
            ProductEditFormValidation
                .validateField(viewModel, 'productDescription', "productDescription")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.succeeded).toBeTruthy();
                    done();
                });

        });

        it('should invalidate when productDescription is less than 10', (done) => {

            // Arrange
            const viewModel = {
                productName: 'arts',
                category: 'BED',
                productDescription: 'productDescription'
            };

            // Act
            ProductEditFormValidation
                .validateField(viewModel, 'productDescription', "Less 10")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.errorMessage).toEqual('The value provided must have at least 10 characters.');
                    done();
                });

        });
    });

    describe('Form Validation', () => {
        it('should validate when all the fields are correct', (done) => {

            // Arrange
            const viewModel = {
                productName: 'arts',
                category: 'BED',
                productDescription: 'productDescription'
            };

            // Act
            ProductEditFormValidation.validateForm(viewModel).then(formValidationResult => {
                // assert
                expect(formValidationResult.succeeded).toBeTruthy();
                done();
            });

        });

        it('should validate when productDescription has more than 250 characters', (done) => {

            // Arrange
            const viewModel = {
                productName: 'arts',
                category: 'BED',
                productDescription: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum'
            };

            // Act
            ProductEditFormValidation.validateForm(viewModel).then(formValidationResult => {
                // assert
                expect(formValidationResult.succeeded).toBeFalsy();
                done();
            });

        });

        it('should validate when some fields are empty', (done) => {

            // Arrange
            const viewModel = {
                productName: '',
                category: 'BED',
                productDescription: 'productDescription'
            };

            // Act
            ProductEditFormValidation.validateForm(viewModel).then(formValidationResult => {
                // assert
                expect(formValidationResult.succeeded).toBeFalsy();
                done();
            });

        });

    });
});