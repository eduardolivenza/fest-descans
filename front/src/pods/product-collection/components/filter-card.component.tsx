import * as React from "react";
import Card from "@material-ui/core/Card";
import { makeStyles } from '@material-ui/core/styles';
import { CardContent, CardHeader, TextField, } from "@material-ui/core";
import { CheckBoxConfigValue, CheckBoxGroup, TextAreaForm } from "common/components";
import Typography from '@material-ui/core/Typography';
import Slider from '@material-ui/core/Slider';

const useStyles = makeStyles(theme => ({
  card: {
    textAlign: "left",
    marginTop: theme.spacing(1),
    marginRight: theme.spacing(1),
  },
  priceNameFilter: {
    width: 300,
  },
  textField: {
    marginRight: theme.spacing(1),
    marginBottom: theme.spacing(2),
  },
}));


interface Props {
  handleChangeComfortFilter: (name: string, value: boolean) => void;
  handleProductTypesFilter: (name: string, value: boolean) => void;
  handleChangePriceFilter: (value: number[]) => void;
  handleChangeFilterText: (name: string) => void;
  comfortLevelFilterState: CheckBoxConfigValue[];
  productTypesFilterState: CheckBoxConfigValue[];
  filterTextValue: string,
  maxPriceValue: number,
  selectedPrice: number[],
}

function valuetext(value) {
  return value + "€";
}

export const FilterCard = (props: Props) => {

  const classes = useStyles({});

  const {
    comfortLevelFilterState,
    handleChangeComfortFilter,
    productTypesFilterState,
    handleProductTypesFilter,
    handleChangeFilterText,
    selectedPrice,
    maxPriceValue,
    filterTextValue,
    handleChangePriceFilter } = props;

  const handleChange = (event, newValue: number[]) => {
    handleChangePriceFilter(newValue);
  };

  const handleChangeText = (event) => {
    handleChangeFilterText(event.target.value);
  };

  return (
    <Card className={classes.card}>
      <CardHeader
        title="Products filter"
      />
      <CardContent>
        <div style={{
          display: "flex",
          justifyContent: 'space-between',
          flexWrap: "wrap",
        }}>
          <CheckBoxGroup
            label={"Filter by comfort level"}
            handleChangeCheckbox={handleChangeComfortFilter}
            items={comfortLevelFilterState}
          />
          <CheckBoxGroup
            label={"Filter by product types"}
            handleChangeCheckbox={handleProductTypesFilter}
            items={productTypesFilterState}
          />
          <div className={classes.priceNameFilter}>
            <Typography id="text-name-filter" gutterBottom>
              Filter by name
            </Typography>
            <TextField
              id="outlined-name"
              className={classes.textField}
              value={filterTextValue}
              onChange={handleChangeText}
              margin="normal"
              variant="outlined"
            />
            <Typography id="range-slider" gutterBottom>
              Price range
            </Typography>
            <Slider
              value={selectedPrice}
              onChange={handleChange}
              valueLabelDisplay="auto"
              max={maxPriceValue}
              aria-labelledby="range-slider"
              getAriaValueText={valuetext}
            />
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

