import * as React from "react";
import { AppLayout, AppUpperMenuLayout } from "layout";
import { SupplierCollectionContainer } from "pods/suppliers-collection";

export const SuppliersCollectionPage = () => (
  <AppUpperMenuLayout>   
    <SupplierCollectionContainer/>
  </AppUpperMenuLayout>
);