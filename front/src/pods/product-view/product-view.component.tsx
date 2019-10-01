import * as React from "react";
import { createStyles, WithStyles, withStyles} from "@material-ui/core/styles";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import { SizesTableView } from 'pods/product-view/product-view-sizes-table.component';
import { ImagesSliderCard } from "common/components/images-slider-card.component";
import Typography from '@material-ui/core/Typography';
import { ValueDisplay } from "common/components";

const styles = () =>
  createStyles({
    formContainer: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "center"
    },
    infoElement:{
      marginTop: '3vh',
      marginBottom: '3vh',
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
      <ImagesSliderCard product={product}/>
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
