import * as React from "react";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import { ProductCollectionCardsComponent } from "./components/product-collection-cards.component";
import { ProductCollectionTableComponent } from "./components/product-collection-table.component";
import {
  ProductsViewSelectorComponent,
  Layout
} from "./components/product-collection-view-selector.component";
import { FilterCard } from "./components/filter-card.component";
import { CheckBoxConfigValue } from "common/components";
import { SessionContext } from "core";
import { AdminAddIcon } from "common/components";
import styles from "common/styles/jss/material-kit-react/views/productsPage.js";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles(styles);

interface Props {
  productCollection: ProductEntityVm[];
  viewProduct: (id: string) => void;
  editProduct: (id: string) => void;
  removeProduct: (id: string) => void;
  addProduct: () => void;
  layout?: Layout;
  handleChangeComfortFilter: (name: string, value: boolean) => void;
  handleProductTypesFilter: (name: string, value: boolean) => void;
  handleChangePriceFilter: (value: number[]) => void;
  handleChangeFilterText: (value: string) => void;
  comfortLevelFilterState: CheckBoxConfigValue[];
  productTypesFilterState: CheckBoxConfigValue[];
  maxPriceValue: number;
  selectedPrice: number[];
  filterTextValue: string;
}

export const ProductCollectionComponent: React.FunctionComponent<
  Props
> = props => {
  const {
    productCollection,
    viewProduct,
    editProduct,
    addProduct,
    removeProduct,
    layout,
    handleChangeComfortFilter,
    handleProductTypesFilter,
    handleChangeFilterText,
    handleChangePriceFilter,
    comfortLevelFilterState,
    productTypesFilterState,
    filterTextValue,
    maxPriceValue,
    selectedPrice
  } = props;

  const [componentLayout, setComponentLayout] = React.useState(layout);
  const session = React.useContext(SessionContext);
  const classes = useStyles({});

  let hotelCollectionComponent;
  if (componentLayout === Layout.Card) {
    hotelCollectionComponent = (
      <ProductCollectionCardsComponent
        productCollection={productCollection}
        viewProduct={viewProduct}
        editProduct={editProduct}
        removeProduct={removeProduct}
      />
    );
  } else if (componentLayout === Layout.Table) {
    hotelCollectionComponent = (
      <ProductCollectionTableComponent
        productCollection={productCollection}
        viewProduct={viewProduct}
      />
    );
  }

  return (

      <div className={classes.section}>
        <h1 className={classes.title}>Products list</h1>
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
        <div style={{ display: "flex" }}>
          <ProductsViewSelectorComponent
            onChangeView={setComponentLayout}
            layout={componentLayout}
          />
          <AdminAddIcon session={session} action={addProduct} />
        </div>
        {hotelCollectionComponent}
      </div>
 
  );
};

ProductCollectionComponent.defaultProps = {
  layout: Layout.Card
};
