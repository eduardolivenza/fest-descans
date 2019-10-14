import * as React from "react";
import { withStyles, createStyles, WithStyles } from "@material-ui/core/styles";
import { SupplierVm } from "core/dataModel/supplier-entity.vm";
import { SupplierCard } from "./supplier-card.component"; // on next step we will create this component

interface Props extends WithStyles<typeof styles> {
  suppliersCollection: SupplierVm[];
  viewSupplier: (id: string) => void;
  editSupplier: (id: string) => void;
  removeSupplier: (id: string) => void;
}

const styles = () =>
  createStyles({
    listLayout: {
      display: "flex",
      flexWrap: "wrap",
      justifyContent: "space-between"
    }
  });

export const SuppliersCollectionCardsComponentInner: React.FunctionComponent<
  Props
> = props => {
  const {
    suppliersCollection,
    classes,
    viewSupplier,
    removeSupplier,
    editSupplier
  } = props;

  return (
    <div className={classes.listLayout}>
      {suppliersCollection.map(product => (
        <SupplierCard
          supplierEntity={product}
          key={product.id}
          viewSupplier={viewSupplier}
          editSupplier={editSupplier}
          removeSupplier={removeSupplier}
        />
      ))}
    </div>
  );
};

export const SuppliersCollectionCardsComponent = withStyles(styles)(
  SuppliersCollectionCardsComponentInner
);
