import * as React from "react";
import Typography from '@material-ui/core/Typography';
import { createStyles, WithStyles, withStyles} from "@material-ui/core/styles";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import { SizesTableView } from 'pods/product-view/product-view-sizes-table.component';
import { ImagesSliderCard } from "common/components/images-slider-card.component"
import { ValueDisplay } from "common/components";

const styles = () =>
  createStyles({
    formContainer: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "center"
    },
    name:{
      marginTop: '3vh',
      marginBottom: '3vh',
    },
    infoElement:{
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
      <Typography className={classes.name} variant="h4" id="productDetailsLabel">Product details</Typography>
      <ImagesSliderCard product={product}/>
      <Typography className={classes.name} variant="h5">{product.productName}</Typography>
      <Typography className={classes.infoElement} variant="subtitle1">Product type: {product.category.description}</Typography>
      <ValueDisplay name="Comfort" value={product.comfortLevel} max={5}/>
      <Typography className={classes.name} variant="subtitle1">Description: {product.productDescription}</Typography>
      <Typography className={classes.infoElement} variant="subtitle1">Produced by {product.supplier.companyName} - {product.supplier.country}</Typography>
      <SizesTableView rows={product.sizes} />
    </div>
  );
};

export const ProductViewComponent = withStyles(styles)(ProductViewComponentInner);
