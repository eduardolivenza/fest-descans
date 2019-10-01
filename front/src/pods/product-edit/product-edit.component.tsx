import * as React from "react";
import { 
  createStyles,
  Theme,
  WithStyles,
  withStyles
} from "@material-ui/core/styles";
import { TextFieldForm, TextAreaForm } from "common/components";
import { Button } from "@material-ui/core";
import { ProductEntityVm, ProductFormErrors } from "core/dataModel/product-entity.vm";
import { LookupEntity } from "core";
import { RatingForm } from "common/components";
import FileUpload from './upload.component';
import SizesTable from "./product-edit-sizes-table.component";
import Typography from '@material-ui/core/Typography';
import { ImagesSliderCard } from "common/components/images-slider-card.component";

const styles = (theme: Theme) =>
  createStyles({
    formContainer: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "center"
    },
    button: {
      marginRight: theme.spacing(1),
      marginBottom: theme.spacing(1),
    },
    rightIcon: {
      marginLeft: theme.spacing(1),
    },
    infoElement:{
      marginTop: '3vh',
      marginBottom: '3vh',
    },
  });

interface Props extends WithStyles<typeof styles> {
  product: ProductEntityVm;
  cities: LookupEntity[];
  onFieldUpdate: (id: string, value: any) => void;
  onSave: () => void;
  onCancel: () => void;
  onChangeFile: (file: File) => void;
  onConfirmSubmit: () => void;
  productFormErrors: ProductFormErrors;
}

export const ProductEditComponentInner = (props: Props) => {

  const { classes, product, cities, onFieldUpdate, onSave,onCancel, onConfirmSubmit, onChangeFile, productFormErrors } = props;

  return (
    <div className={classes.formContainer}>
      <Typography className={classes.infoElement} variant="h4" id="productEditLabel">Product edition</Typography>
      <ImagesSliderCard product={product}/>
      <TextFieldForm
        placeholder="Insert the product name"
        name="productName"
        value={product.productName}
        onChange={onFieldUpdate}
        error={productFormErrors.productName.errorMessage}
      />
      <Typography className={classes.infoElement} variant="subtitle2">Product identifier: {product.productIdentifier}</Typography>
      <RatingForm
        label="Comfort level"
        name="comfortLevel"
        value={product.comfortLevel}
        max={5}
        onChange={onFieldUpdate}
      />
      <TextAreaForm
        className={classes.infoElement} 
        placeholder="Description"
        name="productDescription"
        value={product.productDescription}
        onChange={onFieldUpdate}
        rows={1}
        error={productFormErrors.productDescription.errorMessage}
      />
      <SizesTable onChange={onFieldUpdate} rows={product.sizes}/>
      <FileUpload onChangeFile={onChangeFile} onConfirmSubmit={onConfirmSubmit} />
      <Button name="saveButton" className={classes.button} variant="contained" color="primary" onClick={onSave}>
        Save
      </Button>
      <Button name="cancelButton" className={classes.button} variant="contained" color="primary" onClick={onCancel}>
        Cancel edition
      </Button>
    </div>
  );
};

export const ProductEditComponent = withStyles(styles)(ProductEditComponentInner);
