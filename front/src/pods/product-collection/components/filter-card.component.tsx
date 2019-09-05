import * as React from "react";
import Card from "@material-ui/core/Card";
import { makeStyles } from '@material-ui/core/styles';
import { CardContent, CardHeader, } from "@material-ui/core";
import { CheckBoxConfigValue, CheckBoxGroup } from "common/components";
import Typography from '@material-ui/core/Typography';
import Slider from '@material-ui/core/Slider';

const useStyles = makeStyles(theme => ({
  card: {
    marginTop: theme.spacing(1)
  },
  slider: {
    width: 300,
  },
}));


interface Props {
  handleChangeComfortFilter: (name: string, value: boolean) => void;
  handleProductTypesFilter: (name: string, value: boolean) => void;
  handleChangePriceFilter: (value: number[]) => void;
  comfortLevelFilterState: CheckBoxConfigValue[];
  productTypesFilterState: CheckBoxConfigValue[];
  maxPriceValue: number,
  selectedPrice: number[],
}

function valuetext(value) {
  return `${value}€`;
}

export const FilterCard = (props: Props) => {

  const classes = useStyles({});

  const { comfortLevelFilterState, handleChangeComfortFilter, productTypesFilterState, handleProductTypesFilter, selectedPrice, maxPriceValue, handleChangePriceFilter } = props;

  const handleChange = (event, newValue: number[]) => {
    handleChangePriceFilter(newValue);
  };

  return (
    <Card className={classes.card}>
      <CardHeader
        title="Products filter"
      />
      <CardContent>
        <div style={{
          display: "flex",
          justifyContent: 'space-around',
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
          <div className={classes.slider}>
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

