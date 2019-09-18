import * as React from "react";

import { TableRow, TableCell } from "@material-ui/core";
import { WithStyles, createStyles, withStyles } from "@material-ui/core";
import { ProductEntityVm } from "../product-collection.vm";

interface Props extends WithStyles<typeof styles> {
    product: ProductEntityVm;
    viewProduct: (id: string) => void;
}

const styles = theme => createStyles({
    tableRowHover: {
        '&:hover': {
            backgroundColor: theme.palette.grey[200],
        },
    },
    picture: {
        width: '5rem',
    },
});

const ProductRowComponentInner: React.FunctionComponent<Props> = (props) => {
    const { classes, product, viewProduct } = props;

    return (
        <TableRow key={product.productIdentifier} onDoubleClick={() => viewProduct(product.productIdentifier)} className={classes.tableRowHover}>
             <TableCell align="left" >
                <img  className={classes.picture} src={product.picture} />
            </TableCell>
            <TableCell align="left">{product.productIdentifier}</TableCell>
            <TableCell align="left">{product.productName}</TableCell>
            <TableCell align="left">{product.category}</TableCell>
            <TableCell align="left">{product.comfortLevel}</TableCell>
            <TableCell align="left">{product.productDescription}</TableCell>
        </TableRow>
    );
};

export const ProductRowComponent = withStyles(styles)(
    ProductRowComponentInner
);
