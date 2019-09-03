import * as React from "react";
import Card from "@material-ui/core/Card";
import { makeStyles } from '@material-ui/core/styles';
import { CardContent, CardHeader, } from "@material-ui/core";
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

interface Props {
  handleChangeCheckboxNew: (position: number, value: boolean) => void;
  checkBoxStateNew:boolean[];
}

export const FilterCard = (props: Props) => {

  const classes = useStyles({});

  const { checkBoxStateNew, handleChangeCheckboxNew } = props;

  const handleChangeNew = (event) => {
    handleChangeCheckboxNew(event.target.name, event.target.checked);
  }

  const checkboxes = [
    {
      name: '4',
      label: 'Very hard',
    },
    {
      name: '3',
      label: 'Hard',
    },
    {
      name: '2',
      label: 'Medium',
    },
    {
      name: '1',
      label: 'Soft',
    },
    {
      name: '0',
      label: 'Very soft',
    }
  ];

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
              {
                checkboxes.map(item => (
                  <FormControlLabel
                    control={<Checkbox name={item.name} checked={checkBoxStateNew[parseInt(item.name)]} color="primary" onChange={handleChangeNew} />}
                    label={item.label}
                  />
                ))
              }
            </FormGroup>
          </FormControl>
        </div>
      </CardContent>
    </Card>
  );
};

