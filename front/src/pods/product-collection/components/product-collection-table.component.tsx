import * as React from "react";
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import { Table, TableHead, TableRow, TableCell, TableBody } from "@material-ui/core";
import { ProductRowComponent } from "./product-tableRow.component";

interface Props  {
    productCollection: ProductEntityVm[];
    viewProduct: (id: string) => void;
}

export const ProductCollectionTableComponent = (props: Props) => {
    const { productCollection,  viewProduct } = props;
    return (
        <Table>
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