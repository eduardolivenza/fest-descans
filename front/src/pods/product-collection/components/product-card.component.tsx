import * as React from "react";
import Card from "@material-ui/core/Card";
import { ProductEntityVm, ProductEntitySizeVm } from "../product-collection.vm";
import { Theme } from "@material-ui/core/styles";
import CardHeader from "@material-ui/core/CardHeader/CardHeader";
import IconButton from "@material-ui/core/IconButton/IconButton";
import MoreVertIcon from "@material-ui/icons/MoreVert";
import EditIcon from "@material-ui/icons/edit";
import DeleteIcon from "@material-ui/icons/delete";
import {
  CardContent,
  CardMedia,
  Typography,
  CardActions,
  Chip,
  Avatar
} from "@material-ui/core";
import { withStyles, createStyles, WithStyles } from "@material-ui/core/styles";
import { tsObjectKeyword } from "@babel/types";

interface Props extends WithStyles<typeof styles> {
  product: ProductEntityVm;
  editHotel: (id: string) => void;
}

const styles = (theme: Theme) =>
  createStyles({
    card: {
      width: "500px",
      marginTop: theme.spacing.unit
    },
    chip:
    {
      marginRight: theme.spacing.unit,
    },
    price: {
      fontWeight: 'bold',
      fontSize: 25,
      textAlign: 'right',
    }
  });

const manageProductPrice = () => {
  const [currentPrice, setCurrentPrice] = React.useState<string>(
    "0"
  );

  return { currentPrice, setCurrentPrice };
};


export const ProductCardInner = (props: Props) => {

  const { product, classes, editHotel } = props;
  const { currentPrice, setCurrentPrice } = manageProductPrice();

  const onSizeSelected = (size: ProductEntitySizeVm) => {
    setCurrentPrice(size.price);
  }

  return (
    <Card className={classes.card}>
      <CardHeader
        avatar={<Avatar aria-label="Product">{product.category}</Avatar>}
        action={
          <IconButton>
            <MoreVertIcon />
          </IconButton>
        }
        title={product.productIdentifier}
        subheader={product.category}
      />
      <CardContent>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center"
          }}
        >
          <CardMedia
            image={product.picture}
            title={product.productIdentifier}
            style={{ height: 0, paddingTop: "56.25%" }}
          />

          <Typography variant="subtitle1" gutterBottom>
            {product.productDescription}
          </Typography>
          <Typography className={classes.price}>{currentPrice}€</Typography>
          <div>
            {product.sizes.map(size => (
              <Chip className={classes.chip} color="primary" label={size.size} onClick={() => onSizeSelected(size)} />
            ))}
          </div>

        </div>
      </CardContent>
      <CardActions disableActionSpacing>
        <IconButton aria-label="Add to favorites" onClick={() => editHotel(product.productIdentifier)}>
          <EditIcon />
        </IconButton>
        <IconButton aria-label="Share">
          <DeleteIcon />
        </IconButton>
      </CardActions>
    </Card>
  );
};

export const ProductCard = withStyles(styles)(ProductCardInner);
