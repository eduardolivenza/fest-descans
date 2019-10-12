import * as React from "react";
import TextField from "@material-ui/core/TextField";
import Typography from "@material-ui/core/Typography/Typography";

interface Props {
  name: string;
  placeholder?: string;
  label?: string;
  onChange: (id : string, value : any) => void;
  value: string;
  error?: string;
  type?: string;
}

const onTextFieldChange = (
  fieldId: string,
  onChange: (fieldId, value) => void
) => e => {
  onChange(fieldId, e.target.value);
};

export const TextFieldForm: React.StatelessComponent<Props> = props => {
  const { name, label,placeholder, onChange, value, error, type } = props;
  return (
    <>
      <TextField
        label={label}
        placeholder={placeholder}
        margin="normal"
        value={value}
        type={type}
        onChange={onTextFieldChange(name, onChange)}
      />
      <Typography variant="caption" color="error" gutterBottom>
        {error}
      </Typography>
    </>
  );
};
