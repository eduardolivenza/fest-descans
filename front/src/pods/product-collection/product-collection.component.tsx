import * as React from "react";
import { ProductEntityVm, ProductEntitySizeVm } from "./product-collection.vm";
import { ProductCollectionCardsComponent } from "./components/product-collection-cards.component";
import { ProductCollectionTableComponent } from "./components/product-collection-table.component";
import { HotelCollectionViewSelectorComponent, Layout } from "./components/product-collection-view-selector.component";
import { FilterCard } from "./components/filter-card.component";
import { CheckBoxConfigValue } from "common/components";


interface Props {
  productCollection: ProductEntityVm[];
  viewProduct: (id: string) => void;
  layout?: Layout;
  handleChangeComfortFilter: (name: string, value: boolean) => void;
  comfortLevelFilterState: CheckBoxConfigValue[];
  handleProductTypesFilter: (name: string, value: boolean) => void;
  productTypesFilterState: CheckBoxConfigValue[];
}

export const ProductCollectionComponent: React.FunctionComponent<Props> = (props) => {

  const { productCollection, viewProduct, layout, handleChangeComfortFilter, handleProductTypesFilter, comfortLevelFilterState, productTypesFilterState } = props;
  const [componentLayout, setComponentLayout] = React.useState(layout);

  let hotelCollectionComponent;
  if (componentLayout === Layout.Card) {
    hotelCollectionComponent = <ProductCollectionCardsComponent productCollection={productCollection} viewProduct={viewProduct} />;
  } else if (componentLayout === Layout.Table) {
    hotelCollectionComponent = <ProductCollectionTableComponent productCollection={productCollection} viewProduct={viewProduct} />;
  }

  return (
    <>
      <FilterCard 
        handleChangeComfortFilter={handleChangeComfortFilter} 
        handleProductTypesFilter={handleProductTypesFilter} 
        comfortLevelFilterState={comfortLevelFilterState} 
        productTypesFilterState={productTypesFilterState}/>
      <HotelCollectionViewSelectorComponent onChangeView={setComponentLayout} layout={componentLayout} />
      {hotelCollectionComponent}
    </>
  );
};

ProductCollectionComponent.defaultProps = {
  layout: Layout.Card,
} 