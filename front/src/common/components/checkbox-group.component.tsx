import * as React from "react";
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import FormGroup from '@material-ui/core/FormGroup';
import Checkbox from '@material-ui/core/Checkbox';
import { CheckBoxConfigValue } from "common/components";
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles(theme => ({
    formControl: {
        margin: theme.spacing(2),
    }
}));

interface Props {
    label: string;
    handleChangeCheckbox: (name: string, value: boolean) => void;
    items: CheckBoxConfigValue[];
}

export const CheckBoxGroup = (props: Props) => {

    const classes = useStyles({});
    const { label, handleChangeCheckbox, items } = props;

    const handleChange = (event) => {
        handleChangeCheckbox(event.target.name, event.target.checked);
    }

    return (
        <div
            style={{
                display: "flex",
                flexDirection: "column",
            }} >
            <FormControl component="fieldset" className={classes.formControl}>
                <FormLabel component="legend">{label}</FormLabel>
                <FormGroup>
                    {items.map(item => (
                        <FormControlLabel
                            key={item.name}
                            control={<Checkbox key={item.name} name={item.name} checked={item.value} color="primary" onChange={handleChange} />}
                            label={item.label}
                        />
                    ))
                    }
                </FormGroup>
            </FormControl>
        </div>
    );

}