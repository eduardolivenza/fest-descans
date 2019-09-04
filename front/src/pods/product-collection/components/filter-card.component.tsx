import * as React from "react";
import Card from "@material-ui/core/Card";
import { makeStyles } from '@material-ui/core/styles';
import { CardContent, CardHeader, } from "@material-ui/core";
import { CheckBoxConfigValue, CheckBoxGroup } from "common/components";

const useStyles = makeStyles(theme => ({
  card: {
    marginTop: theme.spacing(1)
  },
}));


interface Props {
  handleChangeComfortFilter: (name: string, value: boolean) => void;
  handleProductTypesFilter: (name: string, value: boolean) => void;
  comfortLevelFilterState: CheckBoxConfigValue[];
  productTypesFilterState: CheckBoxConfigValue[];
}

export const FilterCard = (props: Props) => {

  const classes = useStyles({});

  const { comfortLevelFilterState, handleChangeComfortFilter, productTypesFilterState, handleProductTypesFilter } = props;

  return (
    <Card className={classes.card}>
      <CardHeader
        title="Products filter"
      />
      <CardContent>
        <div style={{
          display: "flex",
          flexDirection: "row",}}>
          <CheckBoxGroup 
            label = {"Filter by comfort level"}
            handleChangeCheckbox={handleChangeComfortFilter}
            items={comfortLevelFilterState}
          />  
          <CheckBoxGroup 
            label = {"Filter by product types"}
            handleChangeCheckbox={handleProductTypesFilter}
            items={productTypesFilterState}
          />  
        </div>
      </CardContent>
    </Card>
  );
};

