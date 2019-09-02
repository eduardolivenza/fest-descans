import * as React from "react";
import { ProductEntityVm, ProductEntitySizeVm } from "./product-collection.vm";
import { HotelCollectionCardsComponent } from "./components/product-collection-cards.component";
import { ProductCollectionTableComponent } from "./components/product-collection-table.component";
import { HotelCollectionViewSelectorComponent, Layout } from "./components/product-collection-view-selector.component";
import {FilterCard} from "./filter-card.component";

interface Props {
  productCollection: ProductEntityVm[];
  viewProduct: (id: string) => void;
  layout?: Layout;
}

export const ProductCollectionComponent: React.FunctionComponent<Props> = (props) => {
  
  const { productCollection, viewProduct, layout} = props;
  const [componentLayout, setComponentLayout] = React.useState(layout);

  let hotelCollectionComponent;
  if (componentLayout === Layout.Card) {
    hotelCollectionComponent = <HotelCollectionCardsComponent productCollection={productCollection} viewProduct={viewProduct} />;
  } else if (componentLayout === Layout.Table) {
    hotelCollectionComponent = <ProductCollectionTableComponent productCollection={productCollection} viewProduct={viewProduct} />;
  }

  return (
    <>
      <FilterCard/>
      <HotelCollectionViewSelectorComponent onChangeView={setComponentLayout} layout={componentLayout} />
      {hotelCollectionComponent}
    </>
  );
};

ProductCollectionComponent.defaultProps = {
  layout: Layout.Card,
} 