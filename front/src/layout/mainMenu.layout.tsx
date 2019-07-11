import * as React from "react";
import { Menu, MenuItem } from "@material-ui/core";
import MenuIcon from '@material-ui/icons/Menu';
import IconButton from "@material-ui/core/IconButton";
import AccountCircle from "@material-ui/icons/AccountCircle";

const menuButtonStyle = {
    marginLeft: -12,
    marginRight: 20,
};

interface Props {
    handleMenu: (event: React.MouseEvent<HTMLElement>) => void;
    openMenu: boolean;
    handleClose: (event: React.MouseEvent<HTMLElement>) => void;
    anchorMenu;
    goToDefault: () => void;
    goToProductsList: () => void;
}

export const MainMenu = (props: Props) => {

    const { anchorMenu, openMenu, handleMenu, handleClose, goToDefault, goToProductsList } = props;

    return (
        <div>
            <IconButton color="inherit" aria-label="Menu" onClick={handleMenu}>
                <MenuIcon />
            </IconButton>
            <Menu
                id="mainMenu-appbar"
                anchorEl={anchorMenu}
                anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                open={openMenu}
                onClose={handleClose}
            >
                <MenuItem onClick={goToDefault}>Default page</MenuItem>
                <MenuItem onClick={goToProductsList}>Products management</MenuItem>
            </Menu>
        </div>

    );
}