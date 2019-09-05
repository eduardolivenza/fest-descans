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
  const [maxPrice, setMaxPrice] = React.useState<number>(0);

  const loadProductsCollection = () =>
    getProductsCollection().then(result => {
      const products: ProductEntityVm[] = mapFromAToBCollection(mapFromApiToVm, result);
      products.map(item =>{
        item.sizes.map( avSize => {
            if (parseInt(avSize.price) > maxPrice) {
              setMaxPrice(parseInt(avSize.price))
            }
        })
      });
      setProductsCollection(products);
      setProductsCollectionFiltered(products);
    }
  );
  return { productsCollection, loadProductsCollection, productsCollectionFiltered, setProductsCollectionFiltered, maxPrice };
};

const comfortLevelCheckboxes: CheckBoxConfigValue[] = [{
  name: '4',
  label: 'Very hard',
  value: true,
}, {
  name: '3',
  label: 'Hard',
  value: true,
}, {
  name: '2',
  label: 'Medium',
  value: true,
}, {
  name: '1',
  label: 'Soft',
  value: true,
}, {
  name: '0',
  label: 'Very soft',
  value: true,
}];

const productTypesCheckBoxes: CheckBoxConfigValue[] = [{
  name: 'BED',
  label: 'Beds and canapes',
  value: true,
}, {
  name: 'MATTRESS',
  label: 'Matresses',
  value: true,
}, {
  name: 'SOFA',
  label: 'Sofas',
  value: true,
}, {
  name: 'OTHER',
  label: 'Pillows and other products',
  value: true,
}];

interface Props extends RouteComponentProps { }

export const ProductCollectionContainerInner = (props: Props) => {

  const { productsCollection, loadProductsCollection, productsCollectionFiltered, setProductsCollectionFiltered, maxPrice } = useProductCollection();

  const [ comfortLevelFilterItems, setComfortLevelFilterItems] = React.useState(comfortLevelCheckboxes);
  const [ productTypesFilterItems, setProductTypesFilterItems] = React.useState(productTypesCheckBoxes);
  const [ priceFilter, setPriceFilter] = React.useState([0, maxPrice]);


  const handleChangeComfortFilter = (name: string, valueEnter: boolean) => {
    let itemIndex: number = comfortLevelFilterItems.findIndex((item) => (item.name === name));
    const newState = [...comfortLevelFilterItems]
    newState[itemIndex].value = valueEnter
    setComfortLevelFilterItems(newState);
  };

  const handleChangeProductTypesFilter = (name: string, valueEnter: boolean) => {
    let itemIndex: number = productTypesFilterItems.findIndex((item) => (item.name === name));
    const newState = [...productTypesFilterItems]
    newState[itemIndex].value = valueEnter
    setProductTypesFilterItems(newState);
  }

  const handleChangePriceFilter = ( newValue: number[]) => {
    setPriceFilter(newValue);
  };

  const viewProduct = (productId: string) => {
    props.history.push(routesLinks.hotelEdit(productId));
  }

  React.useEffect(() => {
    loadProductsCollection();    
  }, []);

  React.useEffect(() => {
    setPriceFilter([0, maxPrice]);
  }, [maxPrice]);
  
  React.useEffect(() => {
    applyFilter();
  }, [ comfortLevelFilterItems, productTypesFilterItems, priceFilter])

  const applyFilter = () => {
    let newArray = productsCollection;
    Object.keys(comfortLevelFilterItems).forEach((position) => {
      if (comfortLevelFilterItems[position].value === false) {
        newArray = newArray.filter(result => (result.comfortLevel != (parseInt(comfortLevelFilterItems[position].name) + 1)));
      }
    });
    Object.keys(productTypesFilterItems).forEach( (position) => {
      if (productTypesFilterItems[position].value === false) {
        newArray = newArray.filter(result => (result.category != (productTypesFilterItems[position].name )));
      }
    });
    newArray = newArray.filter(
        x => x.sizes.some(y => (parseInt(y.price) > priceFilter[0]) && (parseInt(y.price) <= priceFilter[1]))
    );
    setProductsCollectionFiltered(newArray);
  }

  return <ProductCollectionComponent
    productCollection={productsCollectionFiltered}
    viewProduct={viewProduct}
    comfortLevelFilterState={comfortLevelFilterItems}
    handleChangeComfortFilter={handleChangeComfortFilter}
    productTypesFilterState={productTypesFilterItems}
    handleProductTypesFilter={handleChangeProductTypesFilter}
    handleChangePriceFilter={handleChangePriceFilter}
    maxPriceValue={maxPrice}
    selectedPrice={priceFilter}
  />;

};

export const ProductCollectionContainer = withRouter(ProductCollectionContainerInner);