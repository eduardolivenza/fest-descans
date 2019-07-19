import * as React from "react";
import { ProductEntityVm } from "../product-collection.vm";
import { Table, TableHead, TableRow, TableCell, TableBody } from "@material-ui/core";
import { WithStyles, createStyles, Icon, withStyles } from "@material-ui/core";
import { ProductRowComponent } from "./product-tableRow.component";

interface Props extends WithStyles<typeof styles> {
    productCollection: ProductEntityVm[];
    editHotel: (id: string) => void;
}

const styles = theme => createStyles({
    table: {
    },
  
});

const ProductCollectionTableComponentInner = (props: Props) => {
    const { productCollection, classes, editHotel } = props;
    return (
        <Table className={classes.table}>
            <TableHead>
                <TableRow>    
                    <TableCell align="right">Category</TableCell>
                    <TableCell align="right">Product identifier</TableCell>
                    <TableCell align="right">Product description</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
            {
                productCollection.map(product => (<ProductRowComponent key={product.productIdentifier} product={product} editHotel={editHotel}/>)   )
            }
            </TableBody>
        </Table>
    );
}

export const ProductCollectionTableComponent = withStyles(styles)(ProductCollectionTableComponentInner);