import * as React from "react";
import { withStyles, createStyles, WithStyles } from "@material-ui/core/styles";
import { ProductEntityVm, ProductEntitySizeVm } from "core";
import { ProductCard } from "./product-card.component"; // on next step we will create this component

interface Props extends WithStyles<typeof styles> {
  productCollection: ProductEntityVm[];
  addToCart: (product: ProductEntityVm, selectedSize: ProductEntitySizeVm) => void;
  viewProduct: (id: string) => void;
  editProduct: (id: string) => void;
  removeProduct: (id: string) => void;
}

const styles = () => createStyles({
  listLayout: {
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "space-between"
  }
});

export const ProductCollectionCardsComponentInner: React.FunctionComponent<Props> = (props) => {
  
  const { addToCart, productCollection, classes, viewProduct, editProduct, removeProduct} = props;
  
  return (
    <div className={classes.listLayout}>
      {productCollection.map(product => (
        <ProductCard product={product} key={product.internalIdentifier} addToCart={addToCart} viewProduct={viewProduct} editProduct={editProduct} removeProduct={removeProduct}/>
      ))}
    </div>
  );
};

export const ProductCollectionCardsComponent = withStyles(styles)(
  ProductCollectionCardsComponentInner
);
