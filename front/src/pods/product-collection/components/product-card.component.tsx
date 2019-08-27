import * as React from "react";
import Card from "@material-ui/core/Card";
import { ProductEntityVm, ProductEntitySizeVm } from "../product-collection.vm";
import { Theme } from "@material-ui/core/styles";
import CardHeader from "@material-ui/core/CardHeader/CardHeader";
import IconButton from "@material-ui/core/IconButton/IconButton";
import DetailsIcon from "@material-ui/icons/details";
import {
  CardContent,
  Typography,
  CardActions,
  Chip,
  Avatar
} from "@material-ui/core";
import { withStyles, createStyles, WithStyles } from "@material-ui/core/styles";
import { ValueDisplay } from "common/components";
import AwesomeSlider from 'react-awesome-slider';
import 'react-awesome-slider/dist/styles.css';
const logo = require("./../../../images/logo.jpg");
const imageStranger = require("./../../../images/series/stranger-things-2.jpg");


interface Props extends WithStyles<typeof styles> {
  product: ProductEntityVm;
  viewProduct: (id: string) => void;
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
      marginBottom: theme.spacing.unit,
    },
    chipParent: {
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'space-between',
    },
    chips: {
      flexBasis: '80%',
    },
    price: {
      fontWeight: 'bold',
      fontSize: 25,
    }
  });

const manageProductPrice = () => {
  const [currentPrice, setCurrentPrice] = React.useState<string>(
    "--"
  );

  return { currentPrice, setCurrentPrice };
};


export const ProductCardInner = (props: Props) => {

  const { product, classes, viewProduct } = props;
  const { currentPrice, setCurrentPrice } = manageProductPrice();

  const onSizeSelected = (size: ProductEntitySizeVm) => {
    setCurrentPrice(size.price);
  }

  return (
    <Card className={classes.card}>
      <CardHeader
        avatar={<Avatar aria-label="Product">{product.category}</Avatar>}
        title={product.productName}
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

          <AwesomeSlider style={{ marginBottom: '40px' }}>
            <div style={{ backgroundImage: `url(${product.picture})`, backgroundColor: '#ffffff', backgroundRepeat: 'no-repeat', backgroundSize: 'contain', backgroundPosition: '50% 50%' }} />
            <div
              style={{ backgroundColor: '#ffffff' }}
              data-src={imageStranger}
            />
            <div
              style={{ backgroundColor: '#ffffff' }}
              data-src={logo}
            />
          </AwesomeSlider>

          <ValueDisplay
            name="Comfort"
            value={product.comfortLevel}
            max={5}
          />
          <Typography variant="subtitle1" gutterBottom>
            {product.productDescription}
          </Typography>
          <Typography variant="subtitle1" gutterBottom>
            Produced by {product.supplier.companyName} - {product.supplier.country}
          </Typography> 
          <div className={classes.chipParent}>
            <div className={classes.chips}>
            {product.sizes.map(size => (
              <Chip className={classes.chip} color="primary" label={size.size} onClick={() => onSizeSelected(size)} />
            ))}
            </div>
            <Typography className={classes.price}>{currentPrice}€</Typography>
          </div>
        </div>
      </CardContent>
      <CardActions disableActionSpacing>
        <IconButton aria-label="More information" onClick={() => viewProduct(product.productIdentifier)}>
          <DetailsIcon />
        </IconButton>
      </CardActions>
    </Card>
  );
};

export const ProductCard = withStyles(styles)(ProductCardInner);
