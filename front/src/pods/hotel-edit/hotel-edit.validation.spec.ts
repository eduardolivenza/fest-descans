import { FieldValidationResult } from 'lc-form-validation';
import { HotelEditFormValidation } from './hotel-edit.validation';

describe('Hotel Edit Validation tests', () => {
    describe('Field Validations =>', () => {
        it('should invalidate when hotel name does not exist', (done) => {

            // Arrange
            const viewModel = {
                name: 'arts',
                city: 'Barcelona',
                description: 'Description'
            };

            // Act
            HotelEditFormValidation
                .validateField(viewModel, 'name', "")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.errorMessage).toEqual('Please fill in this mandatory field.');
                    done();
                });

        });

        it('should invalidate when city does not exist', (done) => {

            // Arrange
            const viewModel = {
                name: 'arts',
                city: 'Barcelona',
                description: 'Description'
            };

            // Act
            HotelEditFormValidation
                .validateField(viewModel, 'city', "")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.succeeded).toBeFalsy();
                    //expect(fieldValidationResult.errorMessage).toEqual('Please fill in this mandatory field.');
                    done();
                });

        });

        it('should invalidate when description does not exist', (done) => {

            // Arrange
            const viewModel = {
                name: 'arts',
                city: 'Barcelona',
                description: 'Description'
            };

            // Act
            HotelEditFormValidation
                .validateField(viewModel, 'description', "")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.errorMessage).toEqual('Please fill in this mandatory field.');
                    done();
                });

        });

        it('should validate when name is correct', (done) => {

            // Arrange
            const viewModel = {
                name: 'arts',
                city: 'Barcelona',
                description: 'Description'
            };

            // Act
            HotelEditFormValidation
                .validateField(viewModel, 'name', "arts")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.succeeded).toBeTruthy();
                    done();
                });

        });

        it('should validate when city is correct', (done) => {

            // Arrange
            const viewModel = {
                name: 'arts',
                city: 'Barcelona',
                description: 'Description'
            };

            // Act
            HotelEditFormValidation
                .validateField(viewModel, 'city', "Berlin")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.succeeded).toBeTruthy();
                    done();
                });

        });

        it('should validate when description is correct', (done) => {
            // Arrange
            const viewModel = {
                name: 'arts',
                city: 'Barcelona',
                description: 'Description'
            };

            // Act
            HotelEditFormValidation
                .validateField(viewModel, 'description', "Description")
                .then(fieldValidationResult => {
                    // assert
                    expect(fieldValidationResult.succeeded).toBeTruthy();
                    done();
                });

        });

        it('should invalidate when description is less than 10', (done) => {

            // Arrange
            const viewModel = {
                name: 'arts',
                city: 'Barcelona',
                description: 'Description'
            };

            // Act
            HotelEditFormValidation
                .validateField(viewModel, 'description', "Less 10")
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
                name: 'arts',
                city: 'Barcelona',
                description: 'Description'
            };

            // Act
            HotelEditFormValidation.validateForm(viewModel).then(formValidationResult => {
                // assert
                expect(formValidationResult.succeeded).toBeTruthy();
                done();
            });

        });

        it('should validate when description has more than 250 characters', (done) => {

            // Arrange
            const viewModel = {
                name: 'arts',
                city: 'Barcelona',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum'
            };

            // Act
            HotelEditFormValidation.validateForm(viewModel).then(formValidationResult => {
                // assert
                expect(formValidationResult.succeeded).toBeFalsy();
                done();
            });

        });

        it('should validate when some fields are empty', (done) => {

            // Arrange
            const viewModel = {
                name: '',
                city: 'Barcelona',
                description: 'Description'
            };

            // Act
            HotelEditFormValidation.validateForm(viewModel).then(formValidationResult => {
                // assert
                expect(formValidationResult.succeeded).toBeFalsy();
                done();
            });

        });

    });
});