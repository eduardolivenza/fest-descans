import * as React from "react";
import { Menu, MenuItem, ListItemIcon, ListItemText } from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import MenuIcon from "@material-ui/icons/Menu";
import DashboardIcon from "@material-ui/icons/Dashboard";
import ShoppingCartIcon from "@material-ui/icons/ShoppingCart";
import PeopleIcon from "@material-ui/icons/People";

const menuButtonStyle = {
  marginLeft: -12,
  marginRight: 20
};

interface Props {
  handleMenu: (event: React.MouseEvent<HTMLElement>) => void;
  openMenu: boolean;
  handleClose: (event: React.MouseEvent<HTMLElement>) => void;
  anchorMenu;
  goToProducts: () => void;
  goToSuppliers: () => void;
}

export const EncapsulatedMainMenu = (props: Props) => {
  const {
    anchorMenu,
    openMenu,
    handleMenu,
    handleClose,
    goToProducts,
    goToSuppliers
  } = props;

  return (
    <div>
      <IconButton
        style={menuButtonStyle}
        aria-owns={openMenu ? "loginMenu-appbar" : undefined}
        aria-haspopup="true"
        onClick={handleMenu}
        color="inherit"
      >
        <MenuIcon />
      </IconButton>
      <Menu
        id="loginMenu-appbar"
        anchorEl={anchorMenu}
        anchorOrigin={{
          vertical: "top",
          horizontal: "right"
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "right"
        }}
        open={openMenu}
        onClose={handleClose}
      >
        <MenuItem>
          <ListItemIcon>
            <DashboardIcon />
          </ListItemIcon>
          <ListItemText primary="About us" />
        </MenuItem>
        <MenuItem onClick={goToProducts}>
          <ListItemIcon>
            <ShoppingCartIcon />
          </ListItemIcon>
          <ListItemText primary="Our products" />
        </MenuItem>
        <MenuItem onClick={goToSuppliers}>
          <ListItemIcon>
            <PeopleIcon />
          </ListItemIcon>
          <ListItemText primary="Our suppliers" />
        </MenuItem>
      </Menu>
    </div>
  );
};
