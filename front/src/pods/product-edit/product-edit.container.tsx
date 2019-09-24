import * as React from "react";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { productViewRouteParams } from "core";
import { ProductEditComponent } from "./product-edit.component";
import { citiesLookup } from "core";
import { FormValidationResult } from "lc-form-validation";
import { ProductEditFormValidation } from "./product-edit.validation";
import { NotificationComponent } from "common/components";
import { ProductEntityVm, createDefaultProduct, ProductFormErrors, createDefaultProductFormErrors } from "core/dataModel/product-entity.vm";
import { getProductView } from "core/api/product-view.api";
import { mapFromApiToVm } from "core/mapper/product-entity.mapper";

interface Props extends RouteComponentProps {}

const useProductEdit = () => {

  const [product, setProduct] = React.useState<ProductEntityVm>(
    createDefaultProduct()
  );

  const loadProductEdit = (id: number) =>
    getProductView(id).then(result =>
      setProduct(mapFromApiToVm(result))
    );

  return { product, setProduct, loadProductEdit };
};

interface Props extends RouteComponentProps { }

const ProductEditContainerInner = (props: Props) => {

  const [cities] = React.useState(citiesLookup);
  const {product, setProduct, loadProductEdit} = useProductEdit();
  const [productFormErrors, setProductFormErrors] = React.useState<ProductFormErrors>(
    createDefaultProductFormErrors()
  );
  const [showValidationFailedMessage, setShowValidationFailedMessage] = React.useState(false);

  React.useEffect(() => {
    loadProductEdit(props.match.params[productViewRouteParams.id]);
  }, []);

  const onFieldUpdate = (fieldName: keyof ProductEntityVm, value: any) => {
    setProduct({
      ...product,
      [fieldName]: value
    });

    ProductEditFormValidation
      .validateField(product, fieldName, value)
      .then(fieldValidationResult => {
        setProductFormErrors({
          ...productFormErrors,
          [fieldName]: fieldValidationResult
        });
      });
  };

  const doSave = () => {
    ProductEditFormValidation.validateForm(product).then(formValidationResult => {
      handleFormValidation(formValidationResult);
    });
  }

  const handleFormValidation = (formValidation: FormValidationResult) => {
    if (formValidation.succeeded) {
      doSaveServerRequest();
    } else {
      showErrorNotification(formValidation);
    }
  }

  const doSaveServerRequest = () => {
    //save
  }

  const showErrorNotification = (formValidationResult: FormValidationResult) => {
    const updateHotelFormErrors = {
      ...productFormErrors,
      ...formValidationResult.fieldErrors
    };
    setProductFormErrors(updateHotelFormErrors);
    setShowValidationFailedMessage(true);
  }

  return (
    <>
      <ProductEditComponent
        product={product}
        cities={cities}
        onFieldUpdate={onFieldUpdate}
        onSave={doSave}
        productFormErrors={productFormErrors}
      />
      <NotificationComponent
                message="The form contains errors, please check"
                show={showValidationFailedMessage}
                onClose={() => setShowValidationFailedMessage(false)}
            />
    </>
  );
};

export const ProductEditContainer = withRouter(ProductEditContainerInner);

