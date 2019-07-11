import { createStyles, WithStyles, Card, CardHeader, CardContent, Button, withStyles } from "@material-ui/core";
import React from "react";
import { RegisterEntityVm, RegisterFormErrors } from "./register.vm";
import { TextFieldForm } from "common/components";

const styles = theme =>
  createStyles({
    formContainer: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "center",
    },
    buttonContainer: {
      marginBottom: 5,
      justifyContent: "center",
      flex: 1,
      width: "100%",
    },
    button: {
      width: "100%",
    }
  });

interface Props extends WithStyles<typeof styles> {
  onRegister: () => void;
  onCancel: () => void;
  regData: RegisterEntityVm;
  onUpdateRegData: (name: keyof RegisterEntityVm, value: string) => void;
  registerFormErrors: RegisterFormErrors;
}

export const RegisterComponentInner = (props: Props) => {
  
  const {
    classes,
    onCancel,
    onRegister,
    regData,
    onUpdateRegData,
    registerFormErrors,
  } = props;

  return (
    <>
      <Card>
        <CardHeader title="Register new product" />
        <CardContent>
          <div className={classes.formContainer}>
            <TextFieldForm
              label="FirstName"
              name="firstName"
              value={regData.firstName}
              onChange={onUpdateRegData}
            />
            <TextFieldForm
              label="LastName"
              name="lastName"
              value={regData.lastName}
              onChange={onUpdateRegData}
            />
            <TextFieldForm
              label="Email"
              name="email"
              value={regData.email}
              onChange={onUpdateRegData}
              error={registerFormErrors.email.errorMessage}
            />
            <TextFieldForm
              label="Password"
              type="password"
              name="password"
              value={regData.password}
              onChange={onUpdateRegData}
              error={registerFormErrors.password.errorMessage}
            />
            <TextFieldForm
              label="Confirm your password"
              type="password"
              name="passwordConfirm"
              value={regData.passwordConfirm}
              onChange={onUpdateRegData}
              error={registerFormErrors.passwordConfirm.errorMessage}
            />
            <div className={classes.buttonContainer} >
              <Button className={classes.button} variant="contained" color="primary" onClick={onRegister}>
                Register
            </Button>
            </div>
            <div className={classes.buttonContainer} >
              <Button className={classes.button} variant="contained" color="primary" onClick={onCancel}>
                Cancel
            </Button>
            </div>
          </div>
        </CardContent>
      </Card>
    </>
  );
};

export const RegisterComponent = withStyles(styles)(RegisterComponentInner);