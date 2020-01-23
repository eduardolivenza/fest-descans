import * as React from "react";
import { Menu, MenuItem } from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import AccountCircle from "@material-ui/icons/AccountCircle";
import { SessionContext } from "core";

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
    goToRegister: () => void;
}

export const LoginMenu = (props: Props) => {

    const {anchorLoginMenu, openLoginMenu, handleLoginMenu, handleClose, doLoginLogout, goToRegister } = props;
    const session = React.useContext(SessionContext);
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
                <MenuItem onClick={doLoginLogout}> { session.email ? "Logout": "User login"}</MenuItem>
                { session.email ? <MenuItem onClick={goToRegister}>Register</MenuItem>: ''}
            </Menu>
        </div>

    );
}