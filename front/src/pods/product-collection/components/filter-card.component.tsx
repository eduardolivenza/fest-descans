import * as React from "react";
import Card from "@material-ui/core/Card";
import { makeStyles } from '@material-ui/core/styles';
import { CardContent, CardHeader, } from "@material-ui/core";
import { CheckBoxConfigValue, CheckBoxGroup } from "common/components";

const useStyles = makeStyles(theme => ({
  card: {
    marginTop: theme.spacing(1)
  },
  formControl: {
    margin: theme.spacing(2),
  },
  group: {
    margin: theme.spacing(1, 0),
  },
}));

const comfortLevelCheckboxes: CheckBoxConfigValue[] = [{
  name: '4',
  label: 'Very hard',
}, {
  name: '3',
  label: 'Hard',
}, {
  name: '2',
  label: 'Medium',
}, {
  name: '1',
  label: 'Soft',
}, {
  name: '0',
  label: 'Very soft',
}];

const productTypesCheckBoxes: CheckBoxConfigValue[] = [{
  name: 'BED',
  label: 'Beds and canapes',
}, {
  name: 'MATTRESS',
  label: 'Matresses',
}, {
  name: 'SOFA',
  label: 'Sofas',
}, {
  name: 'OTHER',
  label: 'Pillows and other products',
}];

interface Props {
  handleChangeCheckbox: (position: number, value: boolean) => void;
  comfortLevelFilterState: boolean[];
}

export const FilterCard = (props: Props) => {

  const classes = useStyles({});

  const { comfortLevelFilterState, handleChangeCheckbox } = props;

  return (
    <Card className={classes.card}>
      <CardHeader
        title="Products filter"
      />
      <CardContent>
        <div style={{
          display: "flex",
          flexDirection: "row",
        }}>
          <CheckBoxGroup 
            label = {"Filter by products type"}
            handleChangeCheckbox={handleChangeCheckbox}
            states={comfortLevelFilterState}
            items={productTypesCheckBoxes}/>  
          <CheckBoxGroup 
            label = {"Filter by comfort level"}
            handleChangeCheckbox={handleChangeCheckbox}
            states={comfortLevelFilterState}
            items={comfortLevelCheckboxes}/>  
        </div>
      </CardContent>
    </Card>
  );
};

