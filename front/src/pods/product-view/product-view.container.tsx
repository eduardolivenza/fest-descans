import * as React from "react";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { productViewRouteParams } from "core";
import { ProductViewComponent } from "./product-view.component";
import { mapFromApiToVm } from "core/mapper/product-collection.mapper";
import { ProductEntityVm, createDefaultProduct } from "core/dataModel/product-entity.vm";
import { getProductView } from "core/api/product-view.api";

interface Props extends RouteComponentProps { }

const useProductView = () => {

  const [product, setProduct] = React.useState<ProductEntityVm>(
    createDefaultProduct()
  );

  const loadProductView = (id: number) =>
    getProductView(id).then(result =>
      setProduct(mapFromApiToVm(result))
    );

  return { product, loadProductView };
};

interface Props extends RouteComponentProps { }

const ProductViewContainerInner = (props: Props) => {

  const { product, loadProductView } = useProductView();

  React.useEffect(() => {
    loadProductView(props.match.params[productViewRouteParams.id]);
  }, []);

  return (
    <>
      <ProductViewComponent product={product}/>
    </>
  );
};

export const ProductViewContainer = withRouter(ProductViewContainerInner);
