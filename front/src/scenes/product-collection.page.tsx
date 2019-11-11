import * as React from "react";
import { AppLayout, AppUpperMenuLayout } from "layout";
import { ProductCollectionContainer } from "pods/product-collection";

export const ProductCollectionPage = () => (
  <AppUpperMenuLayout>
    <ProductCollectionContainer />
  </AppUpperMenuLayout>
);
