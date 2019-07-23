import * as React from "react";
import { ProductCollectionComponent } from "./product-collection.component";
import { ProductEntityVm, ProductEntitySizeVm} from "./product-collection.vm";
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
  getProductsCollection().then(result =>
    setProductsCollection(mapFromAToBCollection(mapFromApiToVm, result))
    );

  return { productsCollection, loadProductsCollection};
};

interface Props extends RouteComponentProps {}

export const ProductCollectionContainerInner = (props : Props) => {
  const {productsCollection, loadProductsCollection} = useProductCollection();

  const editHotel = (hotelId : string) => {      
    props.history.push(routesLinks.hotelEdit(hotelId));
  }

  React.useEffect(() => {
    loadProductsCollection();
  }, []);

  return  <ProductCollectionComponent 
            productCollection={productsCollection} 
            editHotel = {editHotel}
           
          />;
};

export const ProductCollectionContainer = withRouter<Props>(ProductCollectionContainerInner);

