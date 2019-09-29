import * as React from "react";
import { TableRow, TableCell } from "@material-ui/core";
import { WithStyles, createStyles, withStyles } from "@material-ui/core";
import { ProductEntitySizeVm } from "core/dataModel/product-entity.vm";

interface Props extends WithStyles<typeof styles> {
    productSize: ProductEntitySizeVm;
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

const ProductSizeComponentInner: React.FunctionComponent<Props> = (props) => {
    const { classes, productSize } = props;

    return (
        <TableRow key={productSize.size} className={classes.tableRowHover} >
            <TableCell align="left">{productSize.size}</TableCell>
            <TableCell align="left">{productSize.price}</TableCell>
        </TableRow>
    );
};

export const ProductSizeComponent = withStyles(styles)(
    ProductSizeComponentInner
);
