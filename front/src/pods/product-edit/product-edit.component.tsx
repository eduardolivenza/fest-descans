import * as React from "react";
import {
  createStyles,
  Theme,
  WithStyles,
  withStyles
} from "@material-ui/core/styles";
import { TextFieldForm, TextAreaForm, DropdownForm } from "common/components";
import { Button } from "@material-ui/core";
import {
  ProductEntityVm,
  ProductFormErrors
} from "core/dataModel/product-entity.vm";
import { LookupEntity } from "core";
import { RatingForm } from "common/components";
import FileUpload from "./upload.component";
import SizesTable from "./product-edit-sizes-table.component";
import Typography from "@material-ui/core/Typography";
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
      marginBottom: theme.spacing(1)
    },
    rightIcon: {
      marginLeft: theme.spacing(1)
    },
    titleElement: {
      marginTop: "3vh",
      marginBottom: "3vh"
    },
    descriptionElement: {
      marginTop: "3vh"
    },
    infoElement: {
      marginBottom: "3vh"
    }
  });

interface Props extends WithStyles<typeof styles> {
  product: ProductEntityVm;
  categories: LookupEntity[];
  suppliers: LookupEntity[];
  onFieldUpdate: (id: string, value: any) => void;
  onCategoryUpdate: (id: string, value: any) => void;
  onSupplierUpdate: (id: string, value: any) => void;
  onSave: () => void;
  onCancel: () => void;
  onChangeFile: (file: File) => void;
  onConfirmSubmit: () => void;
  productFormErrors: ProductFormErrors;
}

export const ProductEditComponentInner = (props: Props) => {
  const {
    classes,
    product,
    categories,
    suppliers,
    onFieldUpdate,
    onCategoryUpdate,
    onSupplierUpdate,
    onSave,
    onCancel,
    onConfirmSubmit,
    onChangeFile,
    productFormErrors
  } = props;


  return (
    <div className={classes.formContainer}>
      <Typography
        className={classes.titleElement}
        variant="h4"
        id="productEditLabel"
      >
        Product edition
      </Typography>
      <ImagesSliderCard product={product} />
      <Typography className={classes.descriptionElement} variant="subtitle2">
        Product name
      </Typography>
      <TextFieldForm
        placeholder="Insert here the product name"
        name="productName"
        value={product.productName}
        onChange={onFieldUpdate}
        error={productFormErrors.productName.errorMessage}
      />
      <Typography className={classes.descriptionElement} variant="subtitle2">
        Product identifier
      </Typography>
      <TextFieldForm
        placeholder="Insert here external product identifier"
        name="productIdentifier"
        value={product.productIdentifier}
        onChange={onFieldUpdate}
        error={productFormErrors.productIdentifier.errorMessage}
      />
      <RatingForm
        label="Comfort level"
        name="comfortLevel"
        value={product.comfortLevel}
        max={5}
        onChange={onFieldUpdate}
      />
      <Typography className={classes.descriptionElement} variant="subtitle2">
        Product description
      </Typography>
      <TextAreaForm
        className={classes.infoElement}
        placeholder="Description"
        name="productDescription"
        value={product.productDescription}
        onChange={onFieldUpdate}
        rows={1}
        error={productFormErrors.productDescription.errorMessage}
      />
      <Typography className={classes.descriptionElement} variant="subtitle2">
        Category
      </Typography>
      <DropdownForm
        name="category"
        list={categories}
        value={product.category.value}
        onChange={onCategoryUpdate}
      ></DropdownForm>
      <Typography className={classes.descriptionElement} variant="subtitle2">
        Produced by
      </Typography>
      <DropdownForm
        name="supplier"
        list={suppliers}
        value={product.supplier.id}
        onChange={onSupplierUpdate}
      ></DropdownForm>
      <SizesTable onChange={onFieldUpdate} rows={product.sizes} />
      <FileUpload
        onChangeFile={onChangeFile}
        onConfirmSubmit={onConfirmSubmit}
      />
      <Button
        name="saveButton"
        className={classes.button}
        variant="contained"
        color="primary"
        onClick={onSave}
      >
        Save
      </Button>
      <Button
        name="cancelButton"
        className={classes.button}
        variant="contained"
        color="primary"
        onClick={onCancel}
      >
        Cancel edition
      </Button>
    </div>
  );
};

export const ProductEditComponent = withStyles(styles)(
  ProductEditComponentInner
);
