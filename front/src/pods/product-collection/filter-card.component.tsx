import * as React from "react";
import Card from "@material-ui/core/Card";
import { makeStyles } from '@material-ui/core/styles';
import {
  CardContent,
  CardHeader,
} from "@material-ui/core";
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

export const FilterCard = () => {

  const classes = useStyles({});

  const [state, setState] = React.useState({
    veryhard: true,
    hard: true,
    medium: true,
    soft: true,
    verysoft: true,
  });

  const handleChange = name => event => {
    setState({ ...state, [name]: event.target.checked });
  };

  const { veryhard, hard, medium, soft, verysoft } = state;

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
                control={<Checkbox checked={veryhard} color="primary" onChange={handleChange('veryhard')} value="veryhard" />}
                label="Very hard"
              />
              <FormControlLabel
                control={<Checkbox checked={hard} color="primary" onChange={handleChange('hard')} value="hard" />}
                label="Hard"
              />
              <FormControlLabel
                control={<Checkbox checked={medium} color="primary" onChange={handleChange('medium')} value="medium" />}
                label="Medium"
              />
              <FormControlLabel
                control={<Checkbox checked={soft} color="primary" onChange={handleChange('soft')} value="soft" />}
                label="Soft"
              />
              <FormControlLabel
                control={<Checkbox checked={verysoft} color="primary" onChange={handleChange('verysoft')} value="verysoft" />}
                label="Very soft"
              />
            </FormGroup>
          </FormControl>
        </div>
      </CardContent>
    </Card>
  );
};

