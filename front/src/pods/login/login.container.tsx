import * as React from "react";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { LoginComponent } from "./login.component";
import { routesLinks, setSessionCookie } from "core";
import { LoginEntityVm, createEmptyLogin } from "core/dataModel/login-entity.vm";
import { LoginFormErrors, createDefaultLoginFormErrors } from "./loginFormErrors";
import { validateCredentials } from "core/api/login.api";
import { loginFormValidation } from "./login.validation";
import { NotificationComponent } from "common/components";

interface Props extends RouteComponentProps { }

const LoginContainerInner = (props: Props) => {

  const { history } = props;

  const [loginFormErrors, setLoginFormErrors] = React.useState<LoginFormErrors>(
    createDefaultLoginFormErrors()
  );

  const [credentials, setCredentials] = React.useState<LoginEntityVm>(
    createEmptyLogin()
  );

  const [showLoginFailedMessage, setShowLoginFailedMessage] = React.useState<boolean>(false);

  const doLogin = () => {
    loginFormValidation.validateForm(credentials).then(formValidationResult => {
      if (formValidationResult.succeeded) {
        validateCredentials(credentials).then(
          areValidCredentials => {
            console.log(areValidCredentials.data.token);
            setSessionCookie({
              email: credentials.email,
              token: areValidCredentials.data.token,
            });
            history.push(routesLinks.productCollection);
          }
        ).catch(error => {
          setShowLoginFailedMessage(true);
          console.log(error);
        });
      }
      else {
        alert("error, review the fields");
        const updatedLoginFormErrors = {
          ...loginFormErrors,
          ...formValidationResult.fieldErrors
        };
        setLoginFormErrors(updatedLoginFormErrors);
      }
    });
  };

  const goToRegister = () => {
    history.push(routesLinks.register)
  }

  const goBack = () => {
    history.goBack();
  }

  const onUpdateCredentialsField = (name, value) => {
    setCredentials({
      ...credentials,
      [name]: value
    });

    loginFormValidation
      .validateField(credentials, name, value)
      .then(fieldValidationResult => {
        setLoginFormErrors({
          ...loginFormErrors,
          [name]: fieldValidationResult
        });
      });
  };

  return (
    <>
      <LoginComponent
        onLogin={doLogin}
        onRegister={goToRegister}
        credentials={credentials}
        onUpdateCredentials={onUpdateCredentialsField}
        loginFormErrors={loginFormErrors}
        goBack={goBack}
      />
      <NotificationComponent
        message="Login/password introuced are not correct, please try again"
        show={showLoginFailedMessage}
        onClose={() => setShowLoginFailedMessage(false)}
      />
    </>
  );
};

export const LoginContainer = withRouter(LoginContainerInner);
