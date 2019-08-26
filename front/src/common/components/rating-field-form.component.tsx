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
  onChange: (id: string, value: any) => void;
  value: number;
  max: number;
  error?: string;
}

const onValueFieldChange = (fieldId: string, onChange: (fieldId, value) => void) => value => {
  onChange(fieldId, value);
};

export const RatingFormInner: React.StatelessComponent<Props> = props => {
  const { classes, value, max, onChange, name } = props;
  return (
    <>
    <div className={classes.ratingComponent}>
        <Typography>{name}</Typography>
        <Rating
          value={value}
          max={max}
          onChange={onValueFieldChange(name, onChange)}
        />
        <Typography variant="caption" color="error" gutterBottom>
          {props.error}
        </Typography>
    </div>
    </>
  );
};

export const RatingForm = withStyles(styles)(RatingFormInner);
