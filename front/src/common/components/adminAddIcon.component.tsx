import * as React from "react";
import { Tooltip, IconButton } from "@material-ui/core";
import AddIcon from "@material-ui/icons/Add";
import { SessionContextProps } from "core";

interface Props {
  session: SessionContextProps;
  action: () => void;
}

export const AdminAddIcon = (props: Props) => {
  const { session, action } = props;

  let adminComponents;
  if (session.email) {
    adminComponents = (
      <Tooltip title="AddProduct" onClick={action}>
        <IconButton aria-label="add">
          <AddIcon />
        </IconButton>
      </Tooltip>
    );
  }

  return <div>{adminComponents}</div>;
};
