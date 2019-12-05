import * as React from "react";
import classNames from "classnames";
import { makeStyles } from "@material-ui/core/styles";
import styles from "common/styles/jss/material-kit-react/views/landingPage.js";
import { Copyright } from "layout/copyright";
import { Parallax } from "common/components/Parallax";
import { Header } from "layout/header";
import Grid from "@material-ui/core/Grid";
import { useTranslation } from "react-i18next";
import { Typography } from "@material-ui/core";

const useStyles = makeStyles(styles);

export const LandingComponentParent = props => {
  const classes = useStyles({});
  const { t } = useTranslation();

  return (
    <div>
      <Header color="primary" brand="Fest Descans" fixed />
      {/*color="transparent"
          changeColorOnScroll={{
            height: 250,
            color: "primary"
      }}*/}
      <Parallax filter image={require("public/images/landing-bg.jpg")}>
        <div className={classes.containerParallax}>
          <Grid container spacing={3}>
            <Grid item xs={8}>
            <Typography variant="h4" align="justify" className={classes.title} >
                {t("landing.title")}
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="h5" align="justify" >
                {t("landing.description1")}
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="h6" align="justify">
                {t("landing.description2")}
              </Typography>
            </Grid>
             {/*
            <Grid item xs={12} sm={6}>
              <Typography variant="body1" align="justify">
                {t("landing.description3")}
              </Typography>
            </Grid>
           <Grid item xs={12} sm={6}><h4>2nd column</h4></Grid>*/}
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
