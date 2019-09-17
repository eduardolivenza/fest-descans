import * as React from "react";
import Card from "@material-ui/core/Card";
import CardHeader from "@material-ui/core/CardHeader";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import { createStyles, WithStyles, withStyles } from "@material-ui/core";
import { LoginEntityVm} from "core/dataModel/login-entity.vm"
import { LoginFormErrors } from "./loginFormErrors";
import { TextFieldForm } from "common/components";
import { Link } from "react-router-dom";

const styles = theme =>
  createStyles({
    formContainer: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "center"
    },
    buttonContainer: {
      marginBottom: 5,
      justifyContent: "center",
      flex: 1,
    },
    button: {
      width: "100%",
    }
  });

interface Props extends WithStyles<typeof styles> {
  onLogin: () => void;
  onRegister: () => void;
  goBack: () => void;
  credentials: LoginEntityVm;
  onUpdateCredentials: (name: keyof LoginEntityVm, value: string) => void;
  loginFormErrors: LoginFormErrors;
}



export const LoginComponentInner = (props: Props) => {
  const {
    classes,
    onLogin,
    onRegister,
    credentials,
    onUpdateCredentials,
    loginFormErrors,
    goBack
  } = props;



  return (
    <>
      <Card>
        <CardHeader title="Login" />
        <CardContent>
          <div className={classes.formContainer}>
            <TextFieldForm
              label="User e-mail"
              name="email"
              value={credentials.email}
              onChange={onUpdateCredentials}
              error={loginFormErrors.email.errorMessage}
            />
            <TextFieldForm
              label="Password"
              type="password"
              name="password"
              value={credentials.password}
              onChange={onUpdateCredentials}
              error={loginFormErrors.password.errorMessage}
            />
            <div className={classes.buttonContainer} >
              <Button className={classes.button} variant="contained" color="primary" onClick={onLogin}>
                Login
            </Button>
              </div>
              <div className={classes.buttonContainer} >
              <Button className={classes.button} variant="contained" color="primary" onClick={onRegister}>
                Register new user
            </Button>
            </div>
            <Link style={{textAlign: "center"}} to={""} onClick={goBack}>Back</Link>
          </div>
        </CardContent>
      </Card>
    </>
  );
};

export const LoginComponent = withStyles(styles)(LoginComponentInner);
