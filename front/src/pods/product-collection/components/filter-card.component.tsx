import * as React from "react";
import SearchIcon from '@material-ui/icons/Search';
import { makeStyles } from "@material-ui/core/styles";
import { TextField } from "@material-ui/core";
import {
  CheckBoxConfigValue,
  CheckBoxGroup,

} from "common/components";
import Typography from "@material-ui/core/Typography";
import Slider from "@material-ui/core/Slider";
import ExpansionPanel from "@material-ui/core/ExpansionPanel";
import ExpansionPanelSummary from "@material-ui/core/ExpansionPanelSummary";
import ExpansionPanelDetails from "@material-ui/core/ExpansionPanelDetails";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";

const useStyles = makeStyles(theme => ({
  priceNameFilter: {
    width: 400
  },
  textField: {
    marginRight: theme.spacing(1),
    marginBottom: theme.spacing(2)
  },
  heading: {
    fontSize: theme.typography.pxToRem(15),
    fontWeight: theme.typography.fontWeightBold
  }
}));

interface Props {
  handleChangeComfortFilter: (name: string, value: boolean) => void;
  handleProductTypesFilter: (name: string, value: boolean) => void;
  handleChangePriceFilter: (value: number[]) => void;
  handleChangeFilterText: (name: string) => void;
  comfortLevelFilterState: CheckBoxConfigValue[];
  productTypesFilterState: CheckBoxConfigValue[];
  filterTextValue: string;
  maxPriceValue: number;
  selectedPrice: number[];
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
    handleChangePriceFilter
  } = props;

  const handleChange = (event, newValue: number[]) => {
    handleChangePriceFilter(newValue);
  };

  const handleChangeText = event => {
    handleChangeFilterText(event.target.value);
  };

  return (
    <ExpansionPanel>
      <ExpansionPanelSummary
        expandIcon={<ExpandMoreIcon />}
        aria-controls="panel1a-content"
        id="panel1a-header"
      >
        <SearchIcon/>
        <Typography className={classes.heading}>Filter by...</Typography>
      </ExpansionPanelSummary>
      <ExpansionPanelDetails>
        <div
          style={{
            flexBasis: "60%",
            display: "flex",
            justifyContent: "space-between",
            flexWrap: "wrap"
          }}
        >
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
      </ExpansionPanelDetails>
    </ExpansionPanel>
  );
};
