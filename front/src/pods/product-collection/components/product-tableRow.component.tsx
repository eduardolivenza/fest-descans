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
        <TableRow key={product.email} onDoubleClick={() => editHotel(product.email)} className={classes.tableRowHover}>
            <TableCell align="right" >
                <img  className={classes.picture} src={product.picture} />
            </TableCell>
            <TableCell align="right">{product.firstName}</TableCell>
            <TableCell align="right">{product.lastName}</TableCell>
            <TableCell align="right">{product.email}</TableCell>
        </TableRow>
    );
};

export const ProductRowComponent = withStyles(styles)(
    ProductRowComponentInner
);
