import * as React from "react";
import {
  createStyles,
  WithStyles,
  withStyles
} from "@material-ui/core/styles";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import AwesomeSlider from 'react-awesome-slider';
import 'react-awesome-slider/dist/styles.css';
import { Typography } from "@material-ui/core";

const logo = require("public/images/logo.jpg");
const imageStranger = require("public/images/stranger-things-2.jpg");

const styles = () =>
  createStyles({
    formContainer: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "center"
    },
  });

interface Props extends WithStyles<typeof styles> {
  product: ProductEntityVm;
}

export const ProductViewComponentInner = (props: Props) => {
  const { classes, product } = props;

  return (
    <div className={classes.formContainer}>
      <AwesomeSlider style={{ marginBottom: '6vh', maxWidth: "31.25rem" }}>
        <div key="img2"
          style={{ backgroundColor: '#ffffff' }}
          data-src={imageStranger}
        />
        <div key="img1" style={{ backgroundImage: `url(${product.picture})`, backgroundColor: '#ffffff', backgroundRepeat: 'no-repeat', backgroundSize: 'contain', backgroundPosition: '50% 50%' }} />

        <div key="img3"
          style={{ backgroundColor: '#ffffff' }}
          data-src={logo}
        />
      </AwesomeSlider>
      <Typography >{product.productName}</Typography>
    </div>
  );
};

export const ProductViewComponent = withStyles(styles)(ProductViewComponentInner);
