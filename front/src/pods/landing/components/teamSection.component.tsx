import React from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";

// @material-ui/icons

const team1 = require("public/images/faces/avatar.jpg");
const team2 = require("public/images/faces/christian.jpg");
const team3 = require("public/images/faces/kendall.jpg");

// core components

import styles from "common/styles/jss/material-kit-react/views/landingPageSections/teamStyle.js";

import { Card, CardContent, Typography, CardHeader } from "@material-ui/core";

const useStyles = makeStyles(styles);

export function TeamSection() {
  const classes = useStyles({});
  const imageClasses = classNames(
    classes.imgRaised,
    classes.imgRoundedCircle,
    classes.imgFluid
  );
  return (
    <div className={classes.section}>
      <h2 className={classes.title}>Here is our team</h2>
      <div className={classes.listLayout}>
        
            <Card>
              <CardHeader title="Superboss" subheader="Boss" />
              <CardContent>
                <div
                  style={{
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center"
                  }}
                >
                  <img src={team1} alt="..." className={imageClasses} />
                  <Typography>this is boss card</Typography>
                </div>
              </CardContent>
            </Card>
         
            <Card>
              <CardHeader title="Worker2" subheader="Boss son" />
              <CardContent>
                <div
                  style={{
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center"
                  }}
                >
                   <img src={team2} alt="..." className={imageClasses} />
                  <Typography>this is worker 1 card</Typography>
                </div>
              </CardContent>
            </Card>
            <Card>
              <CardHeader title="Worker3" subheader="worker" />
              <CardContent>
                <div
                  style={{
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center"
                  }}
                >
                  <img src={team3} alt="..." className={imageClasses} />
                  <Typography>this is third worker of the team</Typography>
                </div>
              </CardContent>
            </Card>
        
      </div>
    </div>
  );
}
