import * as React from "react";
import { SuppliersCollectionCardsComponent } from "./suppliers-collection-cards.component";
import { SupplierVm } from "core/dataModel/supplier-entity.vm";

interface Props {
    suppliersCollection: SupplierVm[];
}

export const SuppliersCollectionComponent = (props: Props) => {

    const { suppliersCollection} = props;

    return (
        <div>
           <SuppliersCollectionCardsComponent suppliersCollection={suppliersCollection} />;
        </div>

    );
}