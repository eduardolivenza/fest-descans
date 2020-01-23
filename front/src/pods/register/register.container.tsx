import * as React from "react";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { RegisterEntityVm, createRegisterEntity, RegisterFormErrors, createDefaultRegisterFormErrors } from "./register.vm";
import { RegisterComponent } from "./register.component";
import { registerFormValidation } from "./register.validation";
import { registerNewUser } from "core/api/register.api";
import { NotificationComponent } from "common/components";
import { SessionContext } from "core";

interface Props extends RouteComponentProps { }

export const RegisterContainerInner = (props: Props) => {

  const { history } = props;
  const [registerData, setRegisterData] = React.useState<RegisterEntityVm>(createRegisterEntity());
  const [registerFormErrors, setRegisterFormErrors] = React.useState<RegisterFormErrors>(createDefaultRegisterFormErrors());
  const [showRegisterSuccesfulMessage, setShowRegisterSuccesfulMessage] = React.useState<boolean>(false);
  const [showRegisterErrorMessage, setShowRegisterErrorMessage] = React.useState<boolean>(false);
  const session = React.useContext(SessionContext);

  const registerProduct = () => {
    registerFormValidation.validateForm(registerData).then(formValidationResult => {
      if (formValidationResult.succeeded) {
        console.log(" Register fields valid");
        registerNewUser(registerData, session.token).then(result => {
            if (result.status === 200) {
              setShowRegisterSuccesfulMessage(true);
            }
            else {
              setShowRegisterErrorMessage(true);
            }
        }).catch(error => {
          setShowRegisterErrorMessage(true);
          console.log(error);
        });
      }
    });
  }

  const cancel = () => {
    history.goBack();
  }

  const onUpdateRegisterDataFields = (name, value) => {
    setRegisterData({
      ...registerData,
      [name]: value
    });

    registerFormValidation
      .validateField(registerData, name, value)
      .then(fieldValidationResult => {
        setRegisterFormErrors({
          ...registerFormErrors,
          [name]: fieldValidationResult
        });
      });
  };


  return (
    <>
      <RegisterComponent
        onRegister={registerProduct}
        onCancel={cancel}
        regData={registerData}
        onUpdateRegData={onUpdateRegisterDataFields}
        registerFormErrors={registerFormErrors}
      />
      <NotificationComponent
        message="User registered succesfully"
        show={showRegisterSuccesfulMessage}
        onClose={() => {setShowRegisterSuccesfulMessage(false);
                        history.goBack()}}
      />
      <NotificationComponent
        message="User register fail. Just administrators can create new users into the system"
        show={showRegisterErrorMessage}
        onClose={() => {setShowRegisterErrorMessage(false)}}
      />
    </>
  );
};

export const RegisterContainer = withRouter(RegisterContainerInner);