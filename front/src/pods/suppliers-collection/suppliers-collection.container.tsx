import * as React from "react";
import { SuppliersCollectionComponent } from "./suppliers-collection.component";
import { SupplierVm } from "core/dataModel/supplier-entity.vm";
import { mapFromAToBCollection } from "common";
import { mapSupplierToVm } from "core/mapper/supplier-entity.mapper";
import { getSuppliersCollection } from "core/api/suppliers-collection.api";
import { routesLinks, SessionContext } from "core";
import { withRouter, RouteComponentProps } from "react-router-dom";

interface Props extends RouteComponentProps {}

const useSuppliersCollection = () => {
  const [suppliersCollection, setSuppliersCollection] = React.useState<
    SupplierVm[]
  >([]);

  const loadSuppliersCollection = (token:string) =>
    getSuppliersCollection(token).then(result => {
      const suppliers: SupplierVm[] = mapFromAToBCollection(
        mapSupplierToVm,
        result
      );
      setSuppliersCollection(suppliers);
    });

  return {
    suppliersCollection,
    loadSuppliersCollection
  };
};

export const SupplierCollectionContainerInner = (props: Props) => {
  const session = React.useContext(SessionContext);
  const {
    suppliersCollection,
    loadSuppliersCollection
  } = useSuppliersCollection();

  React.useEffect(() => {
    loadSuppliersCollection(session.token);
  }, []);

  const addSupplier = () => {
    props.history.push(routesLinks.supplierAdd);
  };

  const viewSupplier = (id: string) => {
    alert("viewing supplier details");
  };
  const editSupplier = (id: string) => {
    props.history.push(routesLinks.supplierEdit(id));
  };
  const removeSupplier = (id: string) => {
    alert("removing existing supplier");
  };


  return (
    <SuppliersCollectionComponent
      suppliersCollection={suppliersCollection}
      addSupplier={addSupplier}
      viewSupplier={viewSupplier}
      editSupplier={editSupplier}
      removeSupplier={removeSupplier}
    />
  );
};

export const SupplierCollectionContainer = withRouter(
  SupplierCollectionContainerInner
);
