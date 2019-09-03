import * as React from "react";
import { ProductCollectionComponent } from "./product-collection.component";
import { ProductEntityVm } from "./product-collection.vm";
import { routesLinks } from "core";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { getProductsCollection } from "./product-collection.api";
import { mapFromApiToVm } from "./product-collection.mapper";
import { mapFromAToBCollection } from "common";

const useProductCollection = () => {

  const [productsCollection, setProductsCollection] = React.useState<ProductEntityVm[]>([]);
  const [productsCollectionFiltered, setProductsCollectionFiltered] = React.useState<ProductEntityVm[]>([]);

  const loadProductsCollection = () =>
    getProductsCollection().then(result => {
      const products: ProductEntityVm[] = mapFromAToBCollection(mapFromApiToVm, result);
      setProductsCollection(products);
      setProductsCollectionFiltered(products);
    }
  );

  return { productsCollection, loadProductsCollection, productsCollectionFiltered, setProductsCollectionFiltered };
};

interface Props extends RouteComponentProps { }

export const ProductCollectionContainerInner = (props: Props) => {

  const { productsCollection, loadProductsCollection, productsCollectionFiltered, setProductsCollectionFiltered } = useProductCollection();

  const viewProduct = (productId: string) => {
    props.history.push(routesLinks.hotelEdit(productId));
  }

  React.useEffect(() => {
    loadProductsCollection();
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
  };

  React.useEffect(() => {
    applyFilter();
  }, [comfortFilterState])

  const applyFilter = () => {
    var newArray = productsCollection;
    if (comfortFilterState.veryhard === false) {
      newArray = newArray.filter(result => (result.comfortLevel !== 5));
    }
    if (comfortFilterState.hard === false) {
      newArray = newArray.filter(result => (result.comfortLevel !== 4));
    }
    if (comfortFilterState.medium === false) {
      newArray = newArray.filter(result => (result.comfortLevel !== 3));
    }
    if (comfortFilterState.soft === false) {
      newArray = newArray.filter(result => (result.comfortLevel !== 2));
    }
    if (comfortFilterState.verysoft === false) {
      newArray = newArray.filter(result => (result.comfortLevel !== 1));
    }
    setProductsCollectionFiltered(newArray);
  }

  return <ProductCollectionComponent
    productCollection={productsCollectionFiltered}
    viewProduct={viewProduct}
    comfortLevelState={comfortFilterState}
    handleCheckboxesChange={handleCheckboxesChange}
  />;

};

export const ProductCollectionContainer = withRouter(ProductCollectionContainerInner);