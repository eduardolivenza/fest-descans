import * as React from "react";
import { withRouter, RouteComponentProps } from "react-router-dom";

interface Props extends RouteComponentProps { }

const CartViewContainerInner = (props: Props) => {
  return (
    <>
    Cart
    </>
  );
};

export const CartViewContainer = withRouter(CartViewContainerInner);