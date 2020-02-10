import { ProductCartEntityVm } from "core";
import React from "react";
import {
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody
} from "@material-ui/core";

interface Props {
  cartProductsCollection: ProductCartEntityVm[];
}

export const CartViewComponent = (props: Props) => {
  const { cartProductsCollection } = props;

  return (
    <div>
      {" "}
      <Table>
        <TableHead>
          <TableRow>
            <TableCell align="left">Product name</TableCell>
            <TableCell align="left">Size</TableCell>
            <TableCell align="left">Price</TableCell>
            <TableCell align="left">Quantity</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {cartProductsCollection.map(cartItem => (
            <TableRow key={cartItem.product.internalIdentifier + cartItem.selectedSize.size}>
              <TableCell align="left">{cartItem.product.productName}</TableCell>
              <TableCell align="left">{cartItem.selectedSize.size}</TableCell>
              <TableCell align="left">{cartItem.selectedSize.price}</TableCell>
              <TableCell align="left">{cartItem.quantity}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};
