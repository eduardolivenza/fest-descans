import * as React from "react";
import { Menu, MenuItem } from "@material-ui/core";
import TranslateIcon from '@material-ui/icons/Translate';
import IconButton from "@material-ui/core/IconButton";

const menuButtonStyle = {
    marginLeft: -12,
    marginRight: 20,
};

interface Props {
    handleLanguageMenu : (event: React.MouseEvent<HTMLElement>)  => void;  
    openLanguageMenu : boolean;  
    handleClose : (event: React.MouseEvent<HTMLElement>) => void;  
    anchorLoginMenu;
    setLanguage: (id: string) => void;
}

export const LanguageMenu = (props: Props) => {

    const {anchorLoginMenu, openLanguageMenu, handleLanguageMenu, handleClose, setLanguage } = props;
    return (
        <div>
            <IconButton
                style={menuButtonStyle}
                aria-owns={openLanguageMenu ? 'languageMenu-appbar' : undefined}
                aria-haspopup="true"
                onClick={handleLanguageMenu}
                color="inherit" >
                <TranslateIcon />
            </IconButton>
            <Menu
                id="languageMenu-appbar"
                anchorEl={anchorLoginMenu}
                anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                open={openLanguageMenu}
                onClose={handleClose}
            >
                <MenuItem onClick={() => setLanguage('es')}>ES</MenuItem>
                <MenuItem onClick={() => setLanguage('en')}>EN</MenuItem>
                <MenuItem onClick={() => setLanguage('fr')}>FR</MenuItem>
            </Menu>
        </div>

    );
}