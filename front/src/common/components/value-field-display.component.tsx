import * as React from "react";
import Rating from 'material-ui-rating';
import Typography from "@material-ui/core/Typography";
import {
  createStyles,
  Theme,
  WithStyles,
  withStyles
} from "@material-ui/core/styles";

const styles = (theme: Theme) =>
  createStyles({
    ratingComponent: {
      flex: 3, 
      flexDirection: 'row'
    },
  });

interface Props extends WithStyles<typeof styles>{
  name: string
  value: number;
  max: number;
}

export const ValueDisplayInner: React.StatelessComponent<Props> = props => {
  const { classes, value, max, name } = props;
  return (
    <>
    <div className={classes.ratingComponent}>
        <Typography>{name}</Typography>
        <Rating
          value={value}
          max={max} 
          readOnly={true}
        />
    </div>
    </>
  );
};

export const ValueDisplay = withStyles(styles)(ValueDisplayInner);
