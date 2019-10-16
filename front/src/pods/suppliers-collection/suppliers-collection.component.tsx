import * as React from "react";
import { SuppliersCollectionCardsComponent } from "./suppliers-collection-cards.component";
import { SupplierVm } from "core/dataModel/supplier-entity.vm";
import { SessionContext } from "core";
import { AdminAddIcon } from "common/components";
interface Props {
  suppliersCollection: SupplierVm[];
  addSupplier: () => void;
  viewSupplier: (id: string) => void;
  editSupplier: (id: string) => void;
  removeSupplier: (id: string) => void;
}

export const SuppliersCollectionComponent = (props: Props) => {
  const {
    suppliersCollection,
    addSupplier,
    viewSupplier,
    removeSupplier,
    editSupplier
  } = props;
  const session = React.useContext(SessionContext);
  return (
    <div>
      <AdminAddIcon session={session} action={addSupplier} />
      <SuppliersCollectionCardsComponent
        suppliersCollection={suppliersCollection}
        viewSupplier={viewSupplier}
        editSupplier={editSupplier}
        removeSupplier={removeSupplier}
      />
    </div>
  );
};
