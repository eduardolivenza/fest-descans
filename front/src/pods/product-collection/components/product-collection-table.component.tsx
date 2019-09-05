import * as React from "react";
import { ProductEntityVm } from "../product-collection.vm";
import { Table, TableHead, TableRow, TableCell, TableBody } from "@material-ui/core";
import { WithStyles, createStyles, Icon, withStyles } from "@material-ui/core";
import { ProductRowComponent } from "./product-tableRow.component";

interface Props extends WithStyles<typeof styles> {
    productCollection: ProductEntityVm[];
    viewProduct: (id: string) => void;
}

const styles = theme => createStyles({
    table: {
    },
  
});

const ProductCollectionTableComponentInner = (props: Props) => {
    const { productCollection, classes, viewProduct } = props;
    return (
        <Table className={classes.table}>
            <TableHead>
                <TableRow>    
                    <TableCell align="left">Picture</TableCell>
                    <TableCell align="left">Product identifier</TableCell>
                    <TableCell align="left">Product name</TableCell>
                    <TableCell align="left">Category</TableCell>
                    <TableCell align="left">Comfort level</TableCell>
                    <TableCell align="left">Product description</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
            {
                productCollection.map(product => (<ProductRowComponent key={product.productIdentifier} product={product} viewProduct={viewProduct}/>)   )
            }
            </TableBody>
        </Table>
    );
}

export const ProductCollectionTableComponent = withStyles(styles)(ProductCollectionTableComponentInner);