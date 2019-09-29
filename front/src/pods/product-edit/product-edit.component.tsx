import * as React from "react";
import {
  createStyles,
  Theme,
  WithStyles,
  withStyles
} from "@material-ui/core/styles";
import { Table, TableHead, TableRow, TableCell, TableBody, TextField } from "@material-ui/core";
import { TextFieldForm, TextAreaForm } from "common/components";
import { Button } from "@material-ui/core";
import { ProductEntityVm, ProductFormErrors } from "core/dataModel/product-entity.vm";
import { LookupEntity } from "core";
import { RatingForm } from "common/components";
import AwesomeSlider from 'react-awesome-slider';
import 'react-awesome-slider/dist/styles.css';
import FileUpload from './upload.component';
import { ProductSizeComponent } from "pods/product-edit/productSize-tableRow.component";
import EnhancedTable from "./newtable.component";
import Card from "@material-ui/core/Card";
import { makeStyles } from '@material-ui/core/styles';
import { CardContent, CardHeader } from "@material-ui/core";

const styles = (theme: Theme) =>
  createStyles({
    formContainer: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "center"
    },
    picture: {
      maxWidth: "31.25rem"
    },
    button: {
      marginRight: theme.spacing(1),
      marginBottom: theme.spacing(1),
    },
    rightIcon: {
      marginLeft: theme.spacing(1),
    },
    table: {
      marginTop: theme.spacing(1),
      marginBottom: theme.spacing(5),
    },
    card: {
      marginTop: theme.spacing(1),
      marginRight: theme.spacing(1),
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
      <h1> Product edition</h1>
      <Card className={classes.card}>
      <CardHeader
        title="Product images"
      />
      <CardContent>
      <AwesomeSlider style={{ marginBottom: '6vh', maxWidth: "31.25rem" }}>
        {product.pictures.map(image => (
          <div key="img1" style={{ backgroundImage: `url(${image})`, backgroundColor: '#ffffff', backgroundRepeat: 'no-repeat', backgroundSize: 'contain', backgroundPosition: '50% 50%' }} />
        ))}
      </AwesomeSlider>
      </CardContent>
      </Card>
      <TextFieldForm
        placeholder="Insert the product name"
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
      <TextAreaForm
        placeholder="Description"
        name="productDescription"
        value={product.productDescription}
        onChange={onFieldUpdate}
        rows={1}
        error={productFormErrors.productDescription.errorMessage}
      />

      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <TableCell align="left">Size</TableCell>
            <TableCell align="left">Price</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {
            product.sizes.map(size => (<ProductSizeComponent key={product.productIdentifier + "_" + size.size} productSize={size} />))
          }
        </TableBody>
      </Table>
      <EnhancedTable/>
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
