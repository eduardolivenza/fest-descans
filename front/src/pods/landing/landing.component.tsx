import * as React from "react";
import classNames from "classnames";
import { makeStyles } from "@material-ui/core/styles";
import styles from "common/styles/jss/material-kit-react/views/landingPage.js";
import { Copyright } from "layout/copyright";
import { Parallax } from "common/components/Parallax";
import { Header } from "layout/header";
import Grid from "@material-ui/core/Grid";

const useStyles = makeStyles(styles);

export const LandingComponentParent = props => {
  const classes = useStyles({});

  return (
    <div>
      <Header color="primary" brand="Fest Descans" fixed />
      {/*
          color="transparent"
          changeColorOnScroll={{
            height: 250,
            color: "primary"
          }}*/}
      <Parallax filter image={require("public/images/landing-bg.jpg")}>
        <div className={classes.containerParallax}>
          <Grid container spacing={3}>
            <Grid item xs={8}>
              <h1 className={classes.title}>Welcome to Fest Descans</h1>
            </Grid>
            <Grid item xs={12}>
              <h2>This is a short description</h2>
            </Grid>
            <Grid item xs={12}>
              <h4>
                Every landing page needs a small description after the big bold
                title, that{"'"}s why we added this text here. Add here all the
                information that can make you or your product create the first
                impression.
              </h4>
            </Grid>
            <Grid item xs={12} sm={6}><h4>1st column</h4></Grid>
            <Grid item xs={12} sm={6}><h4>2nd column</h4></Grid>
          </Grid>
        </div>
      </Parallax>
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div className={classes.container}>{props.children}</div>
      </div>
      <Copyright />
    </div>
  );
};
