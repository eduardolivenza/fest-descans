import * as React from "react";
import Rating from '@material-ui/lab/Rating';
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
      flexDirection: 'row',
      marginBottom: '1vh',
      marginTop: '1vh',
    },
  });

interface Props extends WithStyles<typeof styles>{
  name: string;
  label: string;
  onChange: (id: string, value: any) => void;
  value: number;
  max: number;
  error?: string;
}

const onValueFieldChange = (  fieldId: string,  onChange: (fieldId, value) => void) => e => {
  onChange(fieldId, Number(e.target.value));
};

export const RatingFormInner: React.StatelessComponent<Props> = props => {
  const { classes, value, max, onChange, name, label } = props;
  return (
    <>
    <div className={classes.ratingComponent}>
        <Typography variant="subtitle2" style={{marginBottom:'1vh'}}>{label}</Typography>
        <Rating
          name={name}
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
