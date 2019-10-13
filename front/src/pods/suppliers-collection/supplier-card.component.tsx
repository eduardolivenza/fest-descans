import * as React from "react";
import Card from "@material-ui/core/Card";
import {
  SupplierVm,
} from "core/dataModel/supplier-entity.vm";
import { Theme } from "@material-ui/core/styles";
import CardHeader from "@material-ui/core/CardHeader/CardHeader";
import {
  CardContent,
  CardActions,
} from "@material-ui/core";
import { withStyles, createStyles, WithStyles } from "@material-ui/core/styles";
import { SessionContext } from "core";

interface Props extends WithStyles<typeof styles> {
  supplierEntity: SupplierVm;
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
  const { supplierEntity, classes } = props;
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
        >
        </div>
      </CardContent>
      <CardActions disableSpacing>
        
      </CardActions>
    </Card>
  );
};

export const SupplierCard = withStyles(styles)(SupplierCardInner);
