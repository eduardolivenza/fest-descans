import * as React from "react";
import { ProductEntityVm, ProductEntitySizeVm } from "./product-collection.vm";
import { HotelCollectionCardsComponent } from "./components/product-collection-cards.component";
import { ProductCollectionTableComponent } from "./components/product-collection-table.component";
import { HotelCollectionViewSelectorComponent, Layout } from "./components/product-collection-view-selector.component";

interface Props {
  productCollection: ProductEntityVm[];
  viewProduct: (id: string) => void;
  layout?: Layout;
}

export const ProductCollectionComponent: React.FunctionComponent<Props> = (props) => {
  const { productCollection, viewProduct, layout} = props;
  const [componetLayout, setComponentLayout] = React.useState(layout);

  let hotelCollectionComponent;
  if (componetLayout === Layout.Card) {
    hotelCollectionComponent = <HotelCollectionCardsComponent productCollection={productCollection} viewProduct={viewProduct} />;
  } else if (componetLayout === Layout.Table) {
    hotelCollectionComponent = <ProductCollectionTableComponent productCollection={productCollection} viewProduct={viewProduct} />;
  }

  return (
    <>
      <HotelCollectionViewSelectorComponent onChangeView={setComponentLayout} layout={componetLayout} />
      {hotelCollectionComponent}
    </>
  );
};

ProductCollectionComponent.defaultProps = {
  layout: Layout.Card,
} 