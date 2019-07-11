import * as React from "react";
import { ProductEntityVm } from "./product-collection.vm";
import { HotelCollectionCardsComponent } from "./components/product-collection-cards.component";
import { ProductCollectionTableComponent } from "./components/product-collection-table.component";
import { HotelCollectionViewSelectorComponent, Layout } from "./components/product-collection-view-selector.component";

interface Props {
  productCollection: ProductEntityVm[];
  editHotel: (id: string) => void;
  layout?: Layout;
}

export const ProductCollectionComponent: React.FunctionComponent<Props> = (props) => {
  const { productCollection, editHotel, layout } = props;
  const [componetLayout, setComponentLayout] = React.useState(layout);

  let hotelCollectionComponent;
  if (componetLayout === Layout.Card) {
    hotelCollectionComponent = <HotelCollectionCardsComponent productCollection={productCollection} editHotel={editHotel} />;
  } else if (componetLayout === Layout.Table) {
    hotelCollectionComponent = <ProductCollectionTableComponent productCollection={productCollection} editHotel={editHotel} />;
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