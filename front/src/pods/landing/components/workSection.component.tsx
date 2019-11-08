import React from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";

// @material-ui/icons
import styles from "common/styles/jss/material-kit-react/views/landingPageSections/workStyle.js";


const useStyles = makeStyles(styles);

export function WorkSection() {
  const classes = useStyles({});
  return (
    <div className={classes.section}>

          <h2 className={classes.title}>Work with us</h2>
          <h4 className={classes.description}>
            Divide details about your product or agency work into parts. Write a
            few lines about each one and contact us about any further
            collaboration. We will responde get back to you in a couple of
            hours.
          </h4>
    </div>
  );
}
