import * as React from "react";
import TextField from "@material-ui/core/TextField";
import Typography from "@material-ui/core/Typography/Typography";
import { LookupEntity } from "core/model";
import MenuItem from "@material-ui/core/MenuItem";

interface Props {
  name: string;
  label?: string;
  onChange?: (id: string, value: any)  => void;
  value: string;
  error?: string;
  type?: string;
  list: LookupEntity[];
  isDisabled?: boolean;
}

const onTextFieldChange = (
  fieldId: string,
  onChange: (fieldId, value) => void
) => e => {
  onChange(fieldId, e.target.value);
};

export const DropdownForm: React.SFC<Props> = props => {
  const { name, label, onChange, value, error, type, list, isDisabled } = props;
  return (
    <>
      <TextField 
        label={label}
        margin="normal"
        value={value}
        type={type}
        select={true}
        onChange={onTextFieldChange(name, onChange)}
        disabled={isDisabled}
      >
        {list.map(item => (
          <MenuItem key={item.value} value={item.value}>
            {item.description}
          </MenuItem>
        ))}
      </TextField>
      <Typography variant="caption" color="error" gutterBottom>
        {error}
      </Typography>
    </>
  );
};

DropdownForm.defaultProps = {
  type: "text",
  isDisabled: false
};
