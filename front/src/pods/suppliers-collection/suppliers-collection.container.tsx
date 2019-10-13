import * as React from "react";
import { SuppliersCollectionComponent } from "./suppliers-collection.component";
import { SupplierVm } from "core/dataModel/supplier-entity.vm";
import { mapFromAToBCollection } from "common";
import { mapSupplierToVm } from "core/mapper/supplier-entity.mapper";
import { getSuppliersCollection } from "core/api/suppliers-collection.api";

const useSuppliersCollection = () => {
    const [suppliersCollection, setSuppliersCollection] = 
      React.useState<SupplierVm[]>([]);
  
    const loadSuppliersCollection = () =>
        getSuppliersCollection().then(result => {
        const suppliers: SupplierVm[] = mapFromAToBCollection(
            mapSupplierToVm,
            result
        );
        setSuppliersCollection(suppliers);
      });
  
    return {
        suppliersCollection,
        loadSuppliersCollection,
    };
  };
  
export const SuppliersCollectionContainer = () => {
    const {
        suppliersCollection,
        loadSuppliersCollection,
      } = useSuppliersCollection();

    React.useEffect(() => {
        loadSuppliersCollection();
      }, []);
    

    return (
        <SuppliersCollectionComponent suppliersCollection={suppliersCollection}/>
    );
};