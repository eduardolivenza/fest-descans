import * as React from "react";
import { withRouter, RouteComponentProps } from "react-router-dom";
import classNames from "classnames";
import { makeStyles } from "@material-ui/core/styles";
import styles from "common/styles/jss/material-kit-react/views/landingPage.js";
import { Copyright } from "layout/copyright";
import { Parallax } from "common/components/Parallax";
import { Header } from "layout/Header";

interface Props extends RouteComponentProps {}

const useStyles = makeStyles(styles);

const NewDesignLayoutInner: React.FunctionComponent<Props> = props => {
  const classes = useStyles({});

  return (
    <div>
      <Header
        color="transparent"
        brand="Material Kit React"
        fixed
        changeColorOnScroll={{
          height: 300,
          color: "primary"
        }}
      />
      <Parallax filter image={require("public/images/landing-bg.jpg")}>
        <div className={classes.container}>
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <h1 className={classes.title}>Your Story Starts With Us.</h1>
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <h4>
            Every landing page needs a small description after the big bold
            title, that{"'"}s why we added this text here. Add here all the
            information that can make you or your product create the first
            impression.
          </h4>
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
        </div>
      </Parallax>
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div className={classes.container}>{props.children}</div>
      </div>
      <Copyright />
    </div>
  );
};

export const NewDesignLayout = withRouter(NewDesignLayoutInner);
