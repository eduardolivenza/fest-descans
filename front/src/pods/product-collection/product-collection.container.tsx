import * as React from "react";
import { ProductCollectionComponent } from "./product-collection.component";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import { routesLinks } from "core";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { CheckBoxConfigValue } from "common/components";
import { connect } from 'react-redux';
import { Dispatch } from 'redux';
import { modelState } from "store/modelState";
import { handleProductTypesFilterAction, removeProductAction, handleChangeComfortFilterAction, handleChangeFilterTextAction, handleChangePriceFilter } from "pods/product-collection/actions";


const mapStateToProps = (state: modelState) => {
  return {
    productCollection: state.productsCollectionFiltered,
    comfortLevelFilterState: state.comfortLevelCheckboxes,
    productTypesFilterState: state.productTypesFilterItems,
    maxPriceValue: state.maxPrice,
    selectedPrice: state.priceFilter,
    filterTextValue: state.textFilterValue
  };
};
const mapDispatchToProps = (dispatch: Dispatch) => {
  return {
    removeProduct: (id: string) => dispatch(removeProductAction(id)),
    handleChangeComfortFilter: (name: string, value: boolean) => dispatch(handleChangeComfortFilterAction(name, value)),
    handleProductTypesFilter: (name: string, value: boolean) =>dispatch(handleProductTypesFilterAction(name, value)),
    handleChangePriceFilter: (value: number[]) => dispatch(handleChangePriceFilter(value)),
    handleChangeFilterText: (value: string) => dispatch(handleChangeFilterTextAction(value)),
  };
};

interface Props extends RouteComponentProps {
  productCollection: ProductEntityVm[];
  removeProduct: (id: string) => void;
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

export const ProductCollectionComponentInner = (props: Props) => {
  
  const viewProduct = (productId: string) => {
    props.history.push(routesLinks.productView(productId));
  };

  const editProduct = (productId: string) => {
    props.history.push(routesLinks.productEdit(productId));
  };

  const addProduct = () => {
    props.history.push(routesLinks.productAdd);
  };

  return (
    <ProductCollectionComponent
      productCollection={props.productCollection}
      viewProduct={viewProduct}
      editProduct={editProduct}
      addProduct={addProduct}
      removeProduct={props.removeProduct}
      handleChangeComfortFilter={props.handleChangeComfortFilter}
      handleProductTypesFilter={props.handleProductTypesFilter}
      handleChangePriceFilter={props.handleChangePriceFilter}
      handleChangeFilterText={props.handleChangeFilterText}
      comfortLevelFilterState={props.comfortLevelFilterState}
      productTypesFilterState={props.productTypesFilterState}
      maxPriceValue={props.maxPriceValue}
      selectedPrice={props.selectedPrice}
      filterTextValue={props.filterTextValue}
    />
  );

}

export const ProductCollectionContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(withRouter(ProductCollectionComponentInner));

