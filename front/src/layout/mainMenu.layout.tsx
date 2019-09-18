import * as React from "react";
import { List, ListItem, ListItemIcon, ListItemText } from "@material-ui/core";
import DashboardIcon from '@material-ui/icons/Dashboard';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import PeopleIcon from '@material-ui/icons/People';

interface Props {
    goToDefault: () => void;
    goToProductsList: () => void;
    goToSuppliersPage: () => void;
}

export const MainMenu = (props: Props) => {

    const { goToDefault, goToProductsList, goToSuppliersPage } = props;

    return (
        <div>
            <List>
                <ListItem button onClick={goToDefault}>
                    <ListItemIcon>
                        <DashboardIcon />
                    </ListItemIcon>
                    <ListItemText primary="About us" />
                </ListItem>
                <ListItem button onClick={goToProductsList}>
                    <ListItemIcon>
                        <ShoppingCartIcon />
                    </ListItemIcon>
                    <ListItemText primary="Our products" />
                </ListItem>
                <ListItem button onClick={goToSuppliersPage}>
                    <ListItemIcon>
                        <PeopleIcon/>
                    </ListItemIcon>
                    <ListItemText primary="Our suppliers" />
                </ListItem>
            </List>
        </div>
    );
}