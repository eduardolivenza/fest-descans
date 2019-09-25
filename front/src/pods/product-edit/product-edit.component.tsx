import * as React from "react";
import {
  createStyles,
  Theme,
  WithStyles,
  withStyles
} from "@material-ui/core/styles";
import { TextFieldForm, DropdownForm, TextAreaForm } from "common/components";
import { Button } from "@material-ui/core";
import { ProductEntityVm, ProductFormErrors } from "core/dataModel/product-entity.vm";
import { LookupEntity } from "core";
import { RatingForm } from "common/components";
import TextField from "@material-ui/core/TextField";

const styles = (theme: Theme) =>
  createStyles({
    formContainer: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "center"
    },
    picture: {
      maxWidth: "31.25rem"
    }
  });

interface Props extends WithStyles<typeof styles> {
  product: ProductEntityVm;
  cities: LookupEntity[];
  onFieldUpdate: (id: string, value: any) => void;
  onSave: () => void;
  productFormErrors: ProductFormErrors;
}

export const ProductEditComponentInner = (props: Props) => {
  const { classes, product, cities, onFieldUpdate, onSave, productFormErrors } = props;

  return (
    <div className={classes.formContainer}>
      <h1> Product edition</h1>
      <TextFieldForm
        placeholder="Insert product name"
        name="productName"
        value={product.productName}
        onChange={onFieldUpdate}
        error={productFormErrors.productName.errorMessage}
      />
      <RatingForm
        label="Comfort level"
        name="comfortLevel"
        value={product.comfortLevel}
        max={5}
        onChange={onFieldUpdate}
      />
      <img className={classes.picture} src={product.thumbnail} />
      {/*  
     
      <DropdownForm
        name="city"
        label="city"
        onChange={onFieldUpdate}
        value={product.}
        list={cities}
        error={productFormErrors.city.errorMessage}
      />
       */}
      <TextAreaForm
        placeholder="Description"
        name="productDescription"
        value={product.productDescription}
        onChange={onFieldUpdate}
        rows={1}
        error={productFormErrors.productDescription.errorMessage}
      />
      <Button name="saveButton" variant="contained" color="primary" onClick={onSave}>
        Save
      </Button>
    </div>
  );
};

export const ProductEditComponent = withStyles(styles)(ProductEditComponentInner);
