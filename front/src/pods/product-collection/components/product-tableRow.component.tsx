import * as React from "react";

import { TableRow, TableCell } from "@material-ui/core";
import { WithStyles, createStyles, withStyles } from "@material-ui/core";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";

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
        <TableRow key={product.internalIdentifier} onDoubleClick={() => viewProduct(product.internalIdentifier)} className={classes.tableRowHover}>
             <TableCell align="left" >
                <img  className={classes.picture} src={product.thumbnail} />
            </TableCell>
            <TableCell align="left">{product.internalIdentifier}</TableCell>
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
