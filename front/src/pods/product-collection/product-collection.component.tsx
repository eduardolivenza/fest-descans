import * as React from "react";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import { ProductCollectionCardsComponent } from "./components/product-collection-cards.component";
import { ProductCollectionTableComponent } from "./components/product-collection-table.component";
import { ProductsViewSelectorComponent, Layout } from "./components/product-collection-view-selector.component";
import { FilterCard } from "./components/filter-card.component";
import { CheckBoxConfigValue } from "common/components";
import { SessionContext } from "core";
import { Tooltip, IconButton } from "@material-ui/core";
import AddIcon from '@material-ui/icons/Add';

interface Props {
  productCollection: ProductEntityVm[];
  viewProduct: (id: string) => void;
  editProduct: (id: string) => void;
  layout?: Layout;
  handleChangeComfortFilter: (name: string, value: boolean) => void;
  handleProductTypesFilter: (name: string, value: boolean) => void;
  handleChangePriceFilter: (value: number[]) => void;
  handleChangeFilterText: (value: string) => void;
  comfortLevelFilterState: CheckBoxConfigValue[];
  productTypesFilterState: CheckBoxConfigValue[];
  maxPriceValue: number,
  selectedPrice: number[],
  filterTextValue: string,
}

export const ProductCollectionComponent: React.FunctionComponent<Props> = (props) => {

  const {
    productCollection,
    viewProduct,
    editProduct,
    layout,
    handleChangeComfortFilter,
    handleProductTypesFilter,
    handleChangeFilterText,
    handleChangePriceFilter,
    comfortLevelFilterState,
    productTypesFilterState,
    filterTextValue,
    maxPriceValue,
    selectedPrice } = props;

  const [componentLayout, setComponentLayout] = React.useState(layout);
  const session = React.useContext(SessionContext);

  let hotelCollectionComponent;
  if (componentLayout === Layout.Card) {
    hotelCollectionComponent = <ProductCollectionCardsComponent productCollection={productCollection} viewProduct={viewProduct} editProduct={editProduct} />;
  } else if (componentLayout === Layout.Table) {
    hotelCollectionComponent = <ProductCollectionTableComponent productCollection={productCollection} viewProduct={viewProduct} />;
  }
  let adminComponents;
  if (session.email) {
    adminComponents = (
      <Tooltip title="AddProduct" >
        <IconButton aria-label="add">
          <AddIcon />
        </IconButton>
      </Tooltip>
    );
  }

  return (
    <>
      <FilterCard
        handleChangeComfortFilter={handleChangeComfortFilter}
        handleProductTypesFilter={handleProductTypesFilter}
        handleChangePriceFilter={handleChangePriceFilter}
        handleChangeFilterText={handleChangeFilterText}
        comfortLevelFilterState={comfortLevelFilterState}
        productTypesFilterState={productTypesFilterState}
        filterTextValue={filterTextValue}
        maxPriceValue={maxPriceValue}
        selectedPrice={selectedPrice}
      />
      <ProductsViewSelectorComponent onChangeView={setComponentLayout} layout={componentLayout} />
      {adminComponents}
      {hotelCollectionComponent}
    </>
  );
};

ProductCollectionComponent.defaultProps = {
  layout: Layout.Card,
} 