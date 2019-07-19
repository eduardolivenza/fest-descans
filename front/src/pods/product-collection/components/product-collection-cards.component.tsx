import * as React from "react";
import { withStyles, createStyles, WithStyles } from "@material-ui/core/styles";
import { ProductEntityVm } from "../product-collection.vm";
import { ProductCard } from "./product-card.component"; // on next step we will create this component

interface Props extends WithStyles<typeof styles> {  
  productCollection: ProductEntityVm[];
  editHotel : (id : string) => void;
}

const styles = theme => createStyles({
    listLayout: {
      display: "flex",
      flexWrap: "wrap",
      justifyContent: "space-between"
    }
  });

export const HotelCollectionCardsComponentInner : React.FunctionComponent<Props> = (props) => {
  const { productCollection, classes, editHotel } = props;

  return (
    <div className={classes.listLayout}>
      {productCollection.map(product => (
        <ProductCard product={product} key={product.productIdentifier} editHotel={editHotel}/>
      ))}
    </div>
  );
};

export const HotelCollectionCardsComponent = withStyles(styles)(
    HotelCollectionCardsComponentInner
);
