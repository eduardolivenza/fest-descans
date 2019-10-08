import * as React from "react";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { productViewRouteParams } from "core";
import { ProductEditComponent } from "./product-edit.component";
import { categoriesLookup } from "core";
import { FormValidationResult } from "lc-form-validation";
import { ProductEditFormValidation } from "./product-edit.validation";
import { NotificationComponent } from "common/components";
import { ProductEntityVm, createDefaultProduct, ProductFormErrors, createDefaultProductFormErrors } from "core/dataModel/product-entity.vm";
import { getProductView } from "core/api/product-view.api";
import { mapFromApiToVm } from "core/mapper/product-entity.mapper";
import { postImageToProduct} from 'core/api/addProductImage.api';
import { patchProduct } from "core/api/product-patch.api";

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

  const [categories] = React.useState(categoriesLookup);
  const {product, setProduct, loadProductEdit} = useProductEdit();
  const [productFormErrors, setProductFormErrors] = React.useState<ProductFormErrors>(createDefaultProductFormErrors());
  const [showValidationFailedMessage, setShowValidationFailedMessage] = React.useState(false);
  const [file, setFile] = React.useState();
  
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

  const  onChangeCategoryUpdate =  ( id: keyof ProductEntityVm, value: any) => {
    const newValue = {
      value: value,
      description: "",
    }
    onFieldUpdate(id, newValue);
  };

  const doSave = () => {
    ProductEditFormValidation.validateForm(product).then(formValidationResult => {
      handleFormValidation(formValidationResult);
    });
  }

  const doCancel = () => {
    history.back();
  }

  const onConfirmSubmit =() => {
    postImageToProduct(product.productIdentifier, file).then(
      imagePosted => {
        loadProductEdit(props.match.params[productViewRouteParams.id]);
      }
    ).catch(error => {
      console.log(error);
    });
  }

  const onChangeFile = (file:File) => {
    setFile(file);
  }

  const handleFormValidation = (formValidation: FormValidationResult) => {
    if (formValidation.succeeded) {
      patchProduct(product).then( response =>
        history.back()
      )
    } 
    else {
      showErrorNotification(formValidation);
    }
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
        categories={categories}
        onFieldUpdate={onFieldUpdate}
        onChangeCategoryUpdate={onChangeCategoryUpdate}
        onSave={doSave}
        onCancel={doCancel}
        productFormErrors={productFormErrors}
        onConfirmSubmit={onConfirmSubmit}
        onChangeFile={onChangeFile}
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


