import * as React from "react";
import { ProductCollectionComponent } from "./product-collection.component";
import { ProductEntityVm } from "./product-collection.vm";
import { routesLinks } from "core";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { getProductsCollection } from "./product-collection.api";
import { mapFromApiToVm } from "./product-collection.mapper";
import { mapFromAToBCollection } from "common";

const useProductCollection = () => {

  const [productsCollection, setProductsCollection] = React.useState<ProductEntityVm[]>(
    []
  );

  const loadProductsCollection = () =>
    alert("loaded call");
    getProductsCollection().then(result =>
      setProductsCollection(mapFromAToBCollection(mapFromApiToVm, result))
    );

  return { productsCollection, loadProductsCollection };
};

interface Props extends RouteComponentProps { }

export const ProductCollectionContainerInner = (props: Props) => {
  
  const { productsCollection, loadProductsCollection } = useProductCollection();
  const [productsCollectionFiltered, setProductsCollectionFiltered] = React.useState<ProductEntityVm[]>([]);

  const viewProduct = (productId: string) => {
    props.history.push(routesLinks.hotelEdit(productId));
  }

  React.useEffect(() => {
    alert("use effect call")
    loadProductsCollection();
    setProductsCollectionFiltered(productsCollection);
  }, []);

  const [comfortFilterState, setComfortFilter] = React.useState({
    veryhard: true,
    hard: true,
    medium: true,
    soft: true,
    verysoft: true,
  });

  const handleCheckboxesChange = (name: string, value: boolean) => {
    setComfortFilter({ ...comfortFilterState, [name]: value });
    setProductsCollectionFiltered(productsCollection);
  };

  //<TODO> productsCollectionFiltered not loaded correctly
  return <ProductCollectionComponent
    productCollection={productsCollectionFiltered}
    viewProduct={viewProduct}
    comfortLevelState={comfortFilterState}
    handleCheckboxesChange={handleCheckboxesChange}
  />;

};

export const ProductCollectionContainer = withRouter(ProductCollectionContainerInner);