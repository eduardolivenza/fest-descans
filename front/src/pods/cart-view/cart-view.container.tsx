import * as React from "react";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { CartViewComponent } from "pods/cart-view/cart-view.component";
import { ProductCartEntityVm } from "core";
import { cartState } from "pods/cart-view/store/cartState";
import { podCartCollectionSelector } from "pods/cart-view/store/selectors";
import { connect } from "react-redux";
import { Dispatch } from "redux";

interface Props extends RouteComponentProps { 
  addedItems: ProductCartEntityVm[];
}

const mapStateToProps = originalState => {
  const state: cartState = podCartCollectionSelector(originalState);
  return {
    addedItems: state.addedItems,
  };
};

const mapDispatchToProps = (dispatch: Dispatch) => {
  return {
    
  };
};

const CartViewContainerInner = (props: Props) => {
  const {addedItems} = props;
  return (
    <CartViewComponent cartProductsCollection={addedItems}/>
  );
};

export const CartViewContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(withRouter(CartViewContainerInner));