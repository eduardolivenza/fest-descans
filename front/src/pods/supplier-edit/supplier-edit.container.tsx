import * as React from "react";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { productViewRouteParams } from "core";
import { SupplierEditComponent } from "./supplier-edit.component";
import { FormValidationResult } from "lc-form-validation";
import { NotificationComponent } from "common/components";
import {
  SupplierVm,
  createDefaultSupplierFormErrors,
  SupplierFormErrors,
  createDefaultSupplier
} from "core/dataModel/supplier-entity.vm";
import { getSupplier } from "core/api/supplier-get.api";
import { mapSupplierToVm } from "core/mapper/supplier-entity.mapper";
import { SupplierEditFormValidation } from "./supplier-edit.validation";
import { postNewSupplier } from "core/api/supplier-post.api";

interface Props extends RouteComponentProps {}

const useSupplierEdit = () => {
  const [supplier, setSupplier] = React.useState<SupplierVm>(
    createDefaultSupplier()
  );

  const loadSupplierEdit = (id: string) =>
    getSupplier(id).then(result => setSupplier(mapSupplierToVm(result)));

  return {
    supplier,
    setSupplier,
    loadSupplierEdit
  };
};

interface Props extends RouteComponentProps {}

const SupplierEditContainerInner = (props: Props) => {
  const { supplier, setSupplier, loadSupplierEdit } = useSupplierEdit();
  const [supplierId, setSupplierId] = React.useState<string>("");
  const [supplierFormErrors, setSupplierFormErrors] = React.useState<
    SupplierFormErrors
  >(createDefaultSupplierFormErrors());

  const [
    showValidationFailedMessage,
    setShowValidationFailedMessage
  ] = React.useState(false);

  React.useEffect(() => {
    if (props.match.params[productViewRouteParams.id]) {
      setSupplierId(props.match.params[productViewRouteParams.id]);
      loadSupplierEdit(props.match.params[productViewRouteParams.id]);
    }
  }, []);

  const onFieldUpdate = (fieldName: keyof SupplierVm, value: any) => {
    setSupplier({
      ...supplier,
      [fieldName]: value
    });
    SupplierEditFormValidation.validateField(supplier, fieldName, value).then(
      fieldValidationResult => {
        setSupplierFormErrors({
          ...supplierFormErrors,
          [fieldName]: fieldValidationResult
        });
      }
    );
  };

  const doSave = () => {
    SupplierEditFormValidation.validateForm(supplier).then(
      formValidationResult => {
        handleFormValidation(formValidationResult);
      }
    );
  };

  const doCancel = () => {
    history.back();
  };

  const handleFormValidation = (formValidation: FormValidationResult) => {
    if (formValidation.succeeded) {
      if (supplierId !== "") {
        //patchProduct(supplier).then(() => history.back());
      } else {
        postNewSupplier(supplier).then(() => history.back());
      }
    } else {
      showErrorNotification(formValidation);
    }
  };

  const showErrorNotification = (
    formValidationResult: FormValidationResult
  ) => {
    const updateHotelFormErrors = {
      ...supplierFormErrors,
      ...formValidationResult.fieldErrors
    };
    setSupplierFormErrors(updateHotelFormErrors);
    setShowValidationFailedMessage(true);
  };

  return (
    <>
      <SupplierEditComponent
        supplier={supplier}
        supplierId={supplierId}
        onFieldUpdate={onFieldUpdate}
        onSave={doSave}
        onCancel={doCancel}
        supplierFormErrors={supplierFormErrors}
      />
      <NotificationComponent
        message="The form contains errors, please check"
        show={showValidationFailedMessage}
        onClose={() => setShowValidationFailedMessage(false)}
      />
    </>
  );
};

export const SupplierEditContainer = withRouter(SupplierEditContainerInner);
