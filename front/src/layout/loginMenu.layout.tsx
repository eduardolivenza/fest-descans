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
    handleLoginMenu : (event: React.MouseEvent<HTMLElement>)  => void;  
    openLoginMenu : boolean;  
    handleClose : (event: React.MouseEvent<HTMLElement>) => void;  
    anchorLoginMenu;
    doLoginLogout: () => void;
}

export const LoginMenu = (props: Props) => {

    const {anchorLoginMenu, openLoginMenu, handleLoginMenu, handleClose, doLoginLogout } = props;

    return (
        <div>
            <IconButton
                style={menuButtonStyle}
                aria-owns={openLoginMenu ? 'loginMenu-appbar' : undefined}
                aria-haspopup="true"
                onClick={handleLoginMenu}
                color="inherit" >
                <AccountCircle />
            </IconButton>
            <Menu
                id="loginMenu-appbar"
                anchorEl={anchorLoginMenu}
                anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                open={openLoginMenu}
                onClose={handleClose}
            >
                <MenuItem onClick={handleClose}>My account</MenuItem>
                <MenuItem onClick={doLoginLogout}>Login/Register</MenuItem>
            </Menu>
        </div>

    );
}