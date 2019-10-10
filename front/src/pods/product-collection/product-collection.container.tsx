import * as React from "react";
import { ProductCollectionComponent } from "./product-collection.component";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import { routesLinks } from "core";
import { withRouter, RouteComponentProps } from "react-router-dom";
import { getProductsCollection } from "core/api/product-collection.api";
import { mapFromApiToVm } from "core/mapper/product-entity.mapper";
import { mapFromAToBCollection } from "common";
import { CheckBoxConfigValue } from "common/components";

const useProductCollection = () => {
  const [productsCollection, setProductsCollection] = React.useState<
    ProductEntityVm[]
  >([]);
  const [
    productsCollectionFiltered,
    setProductsCollectionFiltered
  ] = React.useState<ProductEntityVm[]>([]);
  const [maxPrice, setMaxPrice] = React.useState<number>(0);

  const loadProductsCollection = () =>
    getProductsCollection().then(result => {
      const products: ProductEntityVm[] = mapFromAToBCollection(
        mapFromApiToVm,
        result
      );
      let maxPriceLocal = maxPrice;
      products.map(item => {
        item.sizes.map(avSize => {
          if (parseInt(avSize.price) >= maxPriceLocal) {
            maxPriceLocal = parseInt(avSize.price);
          }
        });
      });
      setProductsCollection(products);
      setProductsCollectionFiltered(products);
      setMaxPrice(maxPriceLocal);
    });
  return {
    productsCollection,
    loadProductsCollection,
    productsCollectionFiltered,
    setProductsCollectionFiltered,
    maxPrice
  };
};

const comfortLevelCheckboxes: CheckBoxConfigValue[] = [
  {
    name: "4",
    label: "Very hard",
    value: true
  },
  {
    name: "3",
    label: "Hard",
    value: true
  },
  {
    name: "2",
    label: "Medium",
    value: true
  },
  {
    name: "1",
    label: "Soft",
    value: true
  },
  {
    name: "0",
    label: "Very soft",
    value: true
  }
];

const productTypesCheckBoxes: CheckBoxConfigValue[] = [
  {
    name: "BED",
    label: "Beds and canapes",
    value: true
  },
  {
    name: "MATTRESS",
    label: "Matresses",
    value: true
  },
  {
    name: "SOFA",
    label: "Sofas",
    value: true
  },
  {
    name: "OTHER",
    label: "Pillows and other products",
    value: true
  }
];

interface Props extends RouteComponentProps {}

export const ProductCollectionContainerInner = (props: Props) => {
  const {
    productsCollection,
    loadProductsCollection,
    productsCollectionFiltered,
    setProductsCollectionFiltered,
    maxPrice
  } = useProductCollection();
  const [comfortLevelFilterItems, setComfortLevelFilterItems] = React.useState(
    comfortLevelCheckboxes
  );
  const [productTypesFilterItems, setProductTypesFilterItems] = React.useState(
    productTypesCheckBoxes
  );
  const [priceFilter, setPriceFilter] = React.useState([0, maxPrice]);
  const [textFilterValue, setTextFilterValue] = React.useState("");

  const handleChangeComfortFilter = (name: string, valueEnter: boolean) => {
    let itemIndex: number = comfortLevelFilterItems.findIndex(
      item => item.name === name
    );
    const newState = [...comfortLevelFilterItems];
    newState[itemIndex].value = valueEnter;
    setComfortLevelFilterItems(newState);
  };

  const handleChangeProductTypesFilter = (
    name: string,
    valueEnter: boolean
  ) => {
    let itemIndex: number = productTypesFilterItems.findIndex(
      item => item.name === name
    );
    const newState = [...productTypesFilterItems];
    newState[itemIndex].value = valueEnter;
    setProductTypesFilterItems(newState);
  };

  const handleChangePriceFilter = (newValue: number[]) => {
    setPriceFilter(newValue);
  };

  const handleChangeFilterText = (newValue: string) => {
    setTextFilterValue(newValue);
  };

  const viewProduct = (productId: string) => {
    props.history.push(routesLinks.productView(productId));
  };

  const editProduct = (productId: string) => {
    props.history.push(routesLinks.productEdit(productId));
  };

  const removeProduct = (id: string) => {
    alert ("trying to delete " + id)
  };
  const addProduct = () => {
    props.history.push(routesLinks.productAdd);
  };

  React.useEffect(() => {
    loadProductsCollection();
  }, []);

  React.useEffect(() => {
    setPriceFilter([0, maxPrice]);
  }, [maxPrice]);

  React.useEffect(() => {
    applyFilter();
  }, [
    comfortLevelFilterItems,
    productTypesFilterItems,
    priceFilter,
    textFilterValue,
    productsCollection
  ]);

  const applyFilter = () => {
    let newArray = productsCollection;
    Object.keys(comfortLevelFilterItems).forEach(position => {
      if (comfortLevelFilterItems[position].value === false) {
        newArray = newArray.filter(
          result =>
            result.comfortLevel !=
            parseInt(comfortLevelFilterItems[position].name) + 1
        );
      }
    });
    Object.keys(productTypesFilterItems).forEach(position => {
      if (productTypesFilterItems[position].value === false) {
        newArray = newArray.filter(
          result => result.category != productTypesFilterItems[position].name
        );
      }
    });
    newArray = newArray.filter(x =>
      x.sizes.some(
        y =>
          parseInt(y.price) > priceFilter[0] &&
          parseInt(y.price) <= priceFilter[1]
      )
    );
    newArray = newArray.filter(x =>
      x.productName.toLowerCase().includes(textFilterValue.toLowerCase())
    );
    setProductsCollectionFiltered(newArray);
  };

  return (
    <ProductCollectionComponent
      productCollection={productsCollectionFiltered}
      viewProduct={viewProduct}
      editProduct={editProduct}
      addProduct={addProduct}
      removeProduct={removeProduct}
      handleChangeComfortFilter={handleChangeComfortFilter}
      handleProductTypesFilter={handleChangeProductTypesFilter}
      handleChangePriceFilter={handleChangePriceFilter}
      handleChangeFilterText={handleChangeFilterText}
      comfortLevelFilterState={comfortLevelFilterItems}
      productTypesFilterState={productTypesFilterItems}
      maxPriceValue={maxPrice}
      selectedPrice={priceFilter}
      filterTextValue={textFilterValue}
    />
  );
};

export const ProductCollectionContainer = withRouter(
  ProductCollectionContainerInner
);
