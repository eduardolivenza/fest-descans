import * as React from "react";
import { ProductCollectionComponent } from "./product-collection.component";
import { ProductEntityVm } from "./product-collection.vm";
import { routesLinks } from "core";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { getProductsCollection } from "./product-collection.api";
import { mapFromApiToVm } from "./product-collection.mapper";
import { mapFromAToBCollection } from "common";
import { CheckBoxConfigValue } from "common/components";

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

  const [checkedItems, setCheckedItems] = React.useState([true, true, true, true, true]);

  const comfortLevelCheckboxes: CheckBoxConfigValue[]  = [{
      name: '4',
      label: 'Very hard',
    }, {
      name: '3',
      label: 'Hard',
    }, {
      name: '2',
      label: 'Medium',
    }, {
      name: '1',
      label: 'Soft',
    }, {
      name: '0',
      label: 'Very soft',
  }];

  const productTypesCheckBoxes: CheckBoxConfigValue[]  = [{
    name: 'BED',
    label: 'Beds and canapes',
  }, {
    name: 'MATTRESS',
    label: 'Matresses',
  }, {
    name: 'SOFA',
    label: 'Sofas',
  }, {
    name: 'OTHER',
    label: 'Pillows and other products',
}];

  const handleChangeCheckboxNew = (position: number, value: boolean) => {
    setCheckedItems({...checkedItems, [position]: value });
  };

  const viewProduct = (productId: string) => {
    props.history.push(routesLinks.hotelEdit(productId));
  }

  React.useEffect(() => {
    loadProductsCollection();
  }, []);

  React.useEffect(() => {
    applyFilter();
  }, [checkedItems])

  const applyFilter = () => {
    var newArray = productsCollection;
    Object.keys(checkedItems).forEach(function (position) {
      if (checkedItems[position] === false) {
        newArray = newArray.filter(result => (result.comfortLevel != (parseInt(position) + 1)));
      }
    });
    setProductsCollectionFiltered(newArray);
  }

  return <ProductCollectionComponent
    productCollection={productsCollectionFiltered}
    viewProduct={viewProduct}
    checkBoxStateNew={checkedItems}
    handleChangeCheckboxNew={handleChangeCheckboxNew}
    comfortLevelCheckboxes={comfortLevelCheckboxes}
    productTypesCheckBoxes={productTypesCheckBoxes}
  />;

};

export const ProductCollectionContainer = withRouter(ProductCollectionContainerInner);