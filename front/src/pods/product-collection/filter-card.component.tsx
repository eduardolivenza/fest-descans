import * as React from "react";
import Card from "@material-ui/core/Card";
import { makeStyles } from '@material-ui/core/styles';
import {  CardContent, CardHeader, } from "@material-ui/core";
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import FormGroup from '@material-ui/core/FormGroup';
import Checkbox from '@material-ui/core/Checkbox';

const useStyles = makeStyles(theme => ({
  card: {
    marginTop: theme.spacing(1)
  },
  formControl: {
    margin: theme.spacing(3),
  },
  group: {
    margin: theme.spacing(1, 0),
  },
}));

interface Props{
  handleChangeCheckbox: (string, boolean) => void;
  checkBoxState: {
    veryhard: boolean;
    hard: boolean;
    medium: boolean;
    soft: boolean;
    verysoft: boolean;
  }
}

export const FilterCard = (props: Props) => {

  const classes = useStyles({});

  const { checkBoxState, handleChangeCheckbox  } = props;

  const handleChange = (fieldId: string) => e => {
    handleChangeCheckbox(fieldId, e.target.checked);
  };

  return (
    <Card className={classes.card}>
      <CardHeader
        title="Products filter"
      />
      <CardContent>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center"
          }} >
          <FormControl component="fieldset" className={classes.formControl}>
            <FormLabel component="legend">Choose the comfort level</FormLabel>
            <FormGroup>
              <FormControlLabel
                control={<Checkbox checked={checkBoxState.veryhard} color="primary" onChange={handleChange('veryhard')} value="veryhard" />}
                label="Very hard"
              />
              <FormControlLabel
                control={<Checkbox checked={checkBoxState.hard} color="primary" onChange={handleChange('hard')} value="hard" />}
                label="Hard"
              />
              <FormControlLabel
                control={<Checkbox checked={checkBoxState.medium} color="primary" onChange={handleChange('medium')} value="medium" />}
                label="Medium"
              />
              <FormControlLabel
                control={<Checkbox checked={checkBoxState.soft} color="primary" onChange={handleChange('soft')} value="soft" />}
                label="Soft"
              />
              <FormControlLabel
                control={<Checkbox checked={checkBoxState.verysoft} color="primary" onChange={handleChange('verysoft')} value="verysoft" />}
                label="Very soft"
              />
            </FormGroup>
          </FormControl>
        </div>
      </CardContent>
    </Card>
  );
};

