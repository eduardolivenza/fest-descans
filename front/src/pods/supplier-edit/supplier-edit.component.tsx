import * as React from "react";
import {
  createStyles,
  Theme,
  WithStyles,
  withStyles
} from "@material-ui/core/styles";
import { TextFieldForm } from "common/components";
import { Button } from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import { SupplierVm, SupplierFormErrors } from "core/dataModel/supplier-entity.vm";

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
  supplier: SupplierVm;
  onFieldUpdate: (id: string, value: any) => void;
  onSave: () => void;
  onCancel: () => void;
  supplierFormErrors: SupplierFormErrors;
}

export const SupplierEditComponentInner = (props: Props) => {
  const {
    classes,
    supplier,
    onFieldUpdate,
    onSave,
    onCancel,
    supplierFormErrors
  } = props;


  return (
    <div className={classes.formContainer}>
      <Typography
        className={classes.titleElement}
        variant="h4"
        id="productEditLabel"
      >
        Supplier edition
      </Typography>
      <Typography className={classes.descriptionElement} variant="subtitle2">
        Company name
      </Typography>
      <TextFieldForm
        placeholder="Insert here the product name"
        name="companyName"
        value={supplier.companyName}
        onChange={onFieldUpdate}
        error={supplierFormErrors.companyName.errorMessage}
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

export const SupplierEditComponent = withStyles(styles)(
  SupplierEditComponentInner
);
