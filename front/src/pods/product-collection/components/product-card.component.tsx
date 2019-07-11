import * as React from "react";
import Card from "@material-ui/core/Card";
import { ProductEntityVm } from "../product-collection.vm";
import {Theme} from "@material-ui/core/styles";
import CardHeader from "@material-ui/core/CardHeader/CardHeader";
import IconButton from "@material-ui/core/IconButton/IconButton";
import MoreVertIcon from "@material-ui/icons/MoreVert";
import EditIcon from "@material-ui/icons/edit";
import DeleteIcon from "@material-ui/icons/delete";
import {
  CardContent,
  CardMedia,
  Typography,
  CardActions
} from "@material-ui/core";
import { withStyles, createStyles, WithStyles } from "@material-ui/core/styles";

interface Props extends WithStyles<typeof styles> {
  product: ProductEntityVm;
  editHotel : (id : string) => void;  
}

const styles = (theme : Theme)  =>
  createStyles({
    card: {
      width: "500px",
      marginTop: theme.spacing.unit
    }
  });

export const ProductCardInner = (props: Props) => {
  const {product, classes, editHotel} = props;

  return (
    <Card className={classes.card}>
      <CardHeader
        //avatar={<Avatar aria-label="Hotel">{product.rating}</Avatar>}
        action={
          <IconButton>
            <MoreVertIcon />
          </IconButton>
        }
        title={product.firstName}
        subheader={product.email}
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
            title={product.firstName}
            style={{ height: 0, paddingTop: "56.25%" }}
          />
          <Typography variant="subtitle1" gutterBottom>
            Description
          </Typography>
        </div>
      </CardContent>
      <CardActions disableActionSpacing>
        <IconButton aria-label="Add to favorites" onClick={() => editHotel(product.email)}>
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
