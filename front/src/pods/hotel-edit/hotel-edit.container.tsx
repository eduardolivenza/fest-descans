import * as React from "react";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { hotelEditRouteParams } from "core";
import { HotelEditComponent } from "./hotel-edit.component";
import { hotelMockData } from "./hotel-edit.mock";
import { HotelEntityVm, createDefaultHotel, HotelFormErrors, createDefaultHotelFormErrors } from "./hotel-edit.vm";
import { citiesLookup } from "core";
import { mapFromApiToVm } from "./hotel-edit.mapper";
import { getHotelEdit } from './hotel-edit.api';
import { FormValidationResult } from "lc-form-validation";
import { HotelEditFormValidation } from "./hotel-edit.validation";
import { NotificationComponent } from "common/components";

interface Props extends RouteComponentProps {}

const useHotelEdit = () => {
  const [hotel, setHotel] = React.useState<HotelEntityVm>(
    createDefaultHotel()
  );

  
  const loadHotelEdit = (id : number) =>
      getHotelEdit(id).then(result =>
          setHotel(mapFromApiToVm(result))
    );

  return { hotel, setHotel, loadHotelEdit };
};



interface Props extends RouteComponentProps { }

const HotelEditContainerInner = (props: Props) => {
  const [cities] = React.useState(citiesLookup);
  const {hotel, setHotel, loadHotelEdit} = useHotelEdit();
  

  const [hotelFormErrors, setHotelFormErrors] = React.useState<HotelFormErrors>(
    createDefaultHotelFormErrors()
  );

  const [showValidationFailedMessage, setShowValidationFailedMessage] = React.useState(false);

  React.useEffect(() => {
    loadHotelEdit(props.match.params[hotelEditRouteParams.id]);
  }, []);

  const onFieldUpdate = (fieldName: keyof HotelEntityVm, value: any) => {
    setHotel({
      ...hotel,
      [fieldName]: value
    });

    HotelEditFormValidation
      .validateField(hotel, fieldName, value)
      .then(fieldValidationResult => {
        setHotelFormErrors({
          ...hotelFormErrors,
          [fieldName]: fieldValidationResult
        });
      });
  };


  const doSave = () => {
    HotelEditFormValidation.validateForm(hotel).then(formValidationResult => {
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
      ...hotelFormErrors,
      ...formValidationResult.fieldErrors
    };
    setHotelFormErrors(updateHotelFormErrors);
    setShowValidationFailedMessage(true);
  }

  return (
    <>
      <HotelEditComponent
        hotel={hotel}
        cities={cities}
        onFieldUpdate={onFieldUpdate}
        onSave={doSave}
        hotelFormErrors={hotelFormErrors}
      />
      <NotificationComponent
                message="The form contains errors, please check"
                show={showValidationFailedMessage}
                onClose={() => setShowValidationFailedMessage(false)}
            />
    </>
  );
};

export const HotelEditContainer = withRouter<Props>(HotelEditContainerInner);
