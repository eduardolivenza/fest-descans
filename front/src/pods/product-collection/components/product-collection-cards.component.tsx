import * as React from "react";
import { withStyles, createStyles, WithStyles } from "@material-ui/core/styles";
import { ProductEntityVm } from "../product-collection.vm";
import { ProductCard } from "./product-card.component"; // on next step we will create this component

interface Props extends WithStyles<typeof styles> {
  productCollection: ProductEntityVm[];
  viewProduct: (id: string) => void;
}

const styles = () => createStyles({
  listLayout: {
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "space-between"
  }
});

export const ProductCollectionCardsComponentInner: React.FunctionComponent<Props> = (props) => {
  
  const { productCollection, classes, viewProduct } = props;
  
  return (
    <div className={classes.listLayout}>
      {productCollection.map(product => (
        <ProductCard product={product} key={product.productIdentifier} viewProduct={viewProduct} />
      ))}
    </div>
  );
};

export const ProductCollectionCardsComponent = withStyles(styles)(
  ProductCollectionCardsComponentInner
);
