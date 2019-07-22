import * as React from "react";

import { TableRow, TableCell } from "@material-ui/core";
import { WithStyles, createStyles, Icon, withStyles } from "@material-ui/core";
import { ProductEntityVm } from "../product-collection.vm";

interface Props extends WithStyles<typeof styles> {
    product: ProductEntityVm;
    editHotel: (id: string) => void;
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
    const { classes, product, editHotel } = props;

    return (
        <TableRow key={product.productIdentifier} onDoubleClick={() => editHotel(product.productIdentifier)} className={classes.tableRowHover}>
             <TableCell align="right" >
                <img  className={classes.picture} src={product.picture} />
            </TableCell>
            <TableCell align="right">{product.category}</TableCell>
            <TableCell align="right">{product.productIdentifier}</TableCell>
            <TableCell align="right">{product.productDescription}</TableCell>
        </TableRow>
    );
};

export const ProductRowComponent = withStyles(styles)(
    ProductRowComponentInner
);
