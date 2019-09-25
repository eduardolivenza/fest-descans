import * as React from "react";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import { ProductCollectionCardsComponent } from "./components/product-collection-cards.component";
import { ProductCollectionTableComponent } from "./components/product-collection-table.component";
import { HotelCollectionViewSelectorComponent, Layout } from "./components/product-collection-view-selector.component";
import { FilterCard } from "./components/filter-card.component";
import { CheckBoxConfigValue } from "common/components";


interface Props {
  productCollection: ProductEntityVm[];
  viewProduct: (id: string) => void;
  editProduct: (id: string) => void;
  layout?: Layout;
  handleChangeComfortFilter: (name: string, value: boolean) => void;
  comfortLevelFilterState: CheckBoxConfigValue[];
  handleProductTypesFilter: (name: string, value: boolean) => void;
  productTypesFilterState: CheckBoxConfigValue[];
  handleChangePriceFilter: (value: number[]) => void;
  maxPriceValue: number,
  selectedPrice: number[],
}

export const ProductCollectionComponent: React.FunctionComponent<Props> = (props) => {

  const { 
    productCollection,
    viewProduct,
    editProduct,
    layout,
    handleChangeComfortFilter,
    handleProductTypesFilter,
    comfortLevelFilterState,
    productTypesFilterState,
    handleChangePriceFilter,
    maxPriceValue,
    selectedPrice } = props;

  const [componentLayout, setComponentLayout] = React.useState(layout);

  let hotelCollectionComponent;
  if (componentLayout === Layout.Card) {
    hotelCollectionComponent = <ProductCollectionCardsComponent productCollection={productCollection} viewProduct={viewProduct} editProduct={editProduct}/>;
  } else if (componentLayout === Layout.Table) {
    hotelCollectionComponent = <ProductCollectionTableComponent productCollection={productCollection} viewProduct={viewProduct} />;
  }

  return (
    <>
      <FilterCard
        handleChangeComfortFilter={handleChangeComfortFilter}
        handleProductTypesFilter={handleProductTypesFilter}
        comfortLevelFilterState={comfortLevelFilterState}
        productTypesFilterState={productTypesFilterState}
        handleChangePriceFilter={handleChangePriceFilter}
        maxPriceValue={maxPriceValue}
        selectedPrice={selectedPrice}
      />
      <HotelCollectionViewSelectorComponent onChangeView={setComponentLayout} layout={componentLayout} />
      {hotelCollectionComponent}
    </>
  );
};

ProductCollectionComponent.defaultProps = {
  layout: Layout.Card,
} 