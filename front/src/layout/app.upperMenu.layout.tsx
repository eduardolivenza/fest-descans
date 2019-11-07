import * as React from "react";
import classNames from "classnames";
import { makeStyles } from "@material-ui/core/styles";
import styles from "common/styles/jss/material-kit-react/views/landingPage.js";
import { Copyright } from "layout/copyright";
import { Header } from "layout/header";

const useStyles = makeStyles(styles);

export const AppUpperMenuLayout = (props) => {
  const classes = useStyles({});

  return (
    <div>
      <Header color="primary" brand="Fest Descans" fixed />
      <br/>
      <br/>
      <br/>
      <br/>
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div className={classes.container}>
          {props.children}
        </div>
      </div>
      <Copyright />
    </div>
  );
};