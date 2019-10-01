import * as React from "react";
import { createStyles, WithStyles, withStyles} from "@material-ui/core/styles";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import AwesomeSlider from 'react-awesome-slider';
import 'react-awesome-slider/dist/styles.css';
import { SizesTableView } from 'pods/product-view/product-view-sizes-table.component';
import { Card, CardContent, CardHeader } from "@material-ui/core";
import Typography from '@material-ui/core/Typography';
import { ValueDisplay } from "common/components";

const styles = () =>
  createStyles({
    formContainer: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "center"
    },
    card: {
      marginTop: '1vh',
    },
    infoElement:{
      marginTop: '2vh',
      marginBottom: '1vh',
    }
  });

interface Props extends WithStyles<typeof styles> {
  product: ProductEntityVm;
}

export const ProductViewComponentInner = (props: Props) => {
  const { classes, product } = props;

  return (
    <div className={classes.formContainer}>
      <Typography className={classes.infoElement} variant="h4" id="productDetailsLabel">Product details</Typography>
      <Card className={classes.card}>
        <CardHeader
          title="Product images"
        />
        <CardContent>
          <AwesomeSlider style={{ marginBottom: '6vh', maxWidth: "45rem" }}>
            {product.pictures.map(image => (
              <div key={image} style={{ backgroundImage: `url(${image})`, backgroundColor: '#ffffff', backgroundRepeat: 'no-repeat', backgroundSize: 'contain', backgroundPosition: '50% 50%' }} />
            ))}
          </AwesomeSlider>
        </CardContent>
      </Card>
      <Typography className={classes.infoElement} variant="h6">{product.productName}</Typography>
      <ValueDisplay
          name="Comfort"
          value={product.comfortLevel}
          max={5}
      />
      <Typography className={classes.infoElement}variant="subtitle1">{product.productDescription}</Typography>
      <SizesTableView rows={product.sizes} />
    </div>
  );
};

export const ProductViewComponent = withStyles(styles)(ProductViewComponentInner);
