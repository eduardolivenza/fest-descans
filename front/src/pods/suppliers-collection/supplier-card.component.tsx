import * as React from "react";
import Card from "@material-ui/core/Card";
import { SupplierVm } from "core/dataModel/supplier-entity.vm";
import { Theme } from "@material-ui/core/styles";
import CardHeader from "@material-ui/core/CardHeader/CardHeader";
import { CardContent, CardActions } from "@material-ui/core";
import { withStyles, createStyles, WithStyles } from "@material-ui/core/styles";
import { SessionContext } from "core";
import IconButton from "@material-ui/core/IconButton/IconButton";
import DetailsIcon from "@material-ui/icons/details";
import EditIcon from "@material-ui/icons/edit";
import DeleteIcon from "@material-ui/icons/delete";

interface Props extends WithStyles<typeof styles> {
  supplierEntity: SupplierVm;
  viewSupplier: (id: string) => void;
  editSupplier: (id: string) => void;
  removeSupplier: (id: string) => void;
}

const styles = (theme: Theme) =>
  createStyles({
    card: {
      width: "43vh",
      margin: theme.spacing(1)
    },
    chip: {
      marginRight: theme.spacing(1),
      marginBottom: theme.spacing(1)
    },
    chipParent: {
      display: "flex",
      alignItems: "center",
      justifyContent: "space-between"
    },
    chips: {
      flexBasis: "85%"
    },
    price: {
      fontWeight: "bold",
      fontSize: 25
    }
  });

export const SupplierCardInner = (props: Props) => {
  const {
    supplierEntity,
    classes,
    viewSupplier,
    removeSupplier,
    editSupplier
  } = props;
  const session = React.useContext(SessionContext);

  return (
    <Card className={classes.card} key={supplierEntity.id}>
      <CardHeader
        title={supplierEntity.companyName}
        subheader={supplierEntity.country}
      />
      <CardContent>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center"
          }}
        ></div>
      </CardContent>
      <CardActions disableSpacing>
        {session.email ? (
          <>
            <IconButton
              aria-label="Edit"
              onClick={() => editSupplier(supplierEntity.id)}
            >
              <EditIcon />
            </IconButton>
            <IconButton
              aria-label="Remove"
              onClick={() => removeSupplier(supplierEntity.id)}
            >
              <DeleteIcon />
            </IconButton>
          </>
        ) : (
          ""
        )}
        <IconButton
          aria-label="More information"
          onClick={() => viewSupplier(supplierEntity.id)}
        >
          <DetailsIcon />
        </IconButton>
      </CardActions>
    </Card>
  );
};

export const SupplierCard = withStyles(styles)(SupplierCardInner);
