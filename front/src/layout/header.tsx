import React from "react";
import classNames from "classnames";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import styles from "common/styles/jss/material-kit-react/components/headerStyle.js";
import { LanguageMenu } from "layout/menus/languageMenu.layout";
import { LoginMenu } from "layout/menus/loginMenu.layout";
import { useTranslation } from "react-i18next";
import { routesLinks } from "core";
import { withRouter, RouteComponentProps } from "react-router-dom";
import Cookies from "js-cookie";
import { MenuItem, ListItemIcon, ListItemText } from "@material-ui/core";
import DashboardIcon from "@material-ui/icons/Dashboard";
import ShoppingCartIcon from "@material-ui/icons/ShoppingCart";
import PeopleIcon from "@material-ui/icons/People";

const logo = require("public/images/FEST_COLOR_3.png");
const useStyles = makeStyles(styles);

interface Props extends RouteComponentProps {
  color: string;
  brand: string;
  fixed: boolean;
  absolute?: boolean;
  changeColorOnScroll?: {
    height: number;
    color: string;
  };
}

const HeaderInner: React.FunctionComponent<Props> = props => {
  const { history } = props;
  const classes = useStyles({});

  const [anchorLanguageMenu, setAnchorLanguageMenu] = React.useState(null);
  const openLanguageMenu: boolean = Boolean(anchorLanguageMenu);
  const [anchorLoginMenu, setanchorLoginMenu] = React.useState(null);
  const openLoginMenu: boolean = Boolean(anchorLoginMenu);
  const [, setanchorMainMenu] = React.useState(null);

  React.useEffect(() => {
    if (props.changeColorOnScroll) {
      window.addEventListener("scroll", headerColorChange);
    }
    return function cleanup() {
      if (props.changeColorOnScroll) {
        window.removeEventListener("scroll", headerColorChange);
      }
    };
  });

  const handleClose = () => {
    setanchorLoginMenu(null);
    setAnchorLanguageMenu(null);
    setanchorMainMenu(null);
  };

  const handleLoginMenu = (event: React.MouseEvent<HTMLElement>) => {
    setanchorLoginMenu(event.currentTarget);
  };

  const { i18n } = useTranslation();

  const changeLanguage = lng => {
    i18n.changeLanguage(lng);
  };

  const handleLanguageMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorLanguageMenu(event.currentTarget);
  };


  const headerColorChange = () => {
    const { color, changeColorOnScroll } = props;
    const windowsScrollTop = window.pageYOffset;
    if (windowsScrollTop > changeColorOnScroll.height) {
      document.body
        .getElementsByTagName("header")[0]
        .classList.remove(classes[color]);
      document.body
        .getElementsByTagName("header")[0]
        .classList.add(classes[changeColorOnScroll.color]);
    } else {
      document.body
        .getElementsByTagName("header")[0]
        .classList.add(classes[color]);
      document.body
        .getElementsByTagName("header")[0]
        .classList.remove(classes[changeColorOnScroll.color]);
    }
  };

  const doLoginLogout = () => {
    Cookies.remove("session");
    history.push("/login");
  };

  const { color, fixed, absolute } = props;
  const appBarClasses = classNames({
    [classes.appBar]: true,
    [classes[color]]: color,
    [classes.absolute]: absolute,
    [classes.fixed]: fixed
  });

  const goToLandingPage= () => {
    history.push(routesLinks.default);
  };

  const goToProductsList = () => {
    history.push(routesLinks.productCollection);
  };

  const goToSuppliersPage = () => {
    history.push(routesLinks.suppliersCollection);
  };

  return (
    <AppBar className={appBarClasses}>
      <Toolbar className={classes.toolBarContainer}>
        <div className={classes.toolBarIcons}>
          <img src={logo} alt="Logo" height="100vh" />
          <MenuItem onClick={goToLandingPage}>
            <ListItemIcon>
              <DashboardIcon  className={classes.whiteIcon}/>
            </ListItemIcon>
            <ListItemText primary=" About us" />
          </MenuItem>
          <MenuItem onClick={goToProductsList}>
            <ListItemIcon>
              <ShoppingCartIcon  className={classes.whiteIcon}/>
            </ListItemIcon>
            <ListItemText primary=" Products list" />
          </MenuItem>
          <MenuItem onClick={goToSuppliersPage}>
            <ListItemIcon>
              <PeopleIcon className={classes.whiteIcon}/>
            </ListItemIcon>
            <ListItemText primary=" Suppliers" />
          </MenuItem>
        </div>

        <div className={classes.toolBarIcons}>
          <LanguageMenu
            handleLanguageMenu={handleLanguageMenu}
            openLanguageMenu={openLanguageMenu}
            handleClose={handleClose}
            anchorLoginMenu={anchorLanguageMenu}
            setLanguage={changeLanguage}
          />
          <LoginMenu
            handleLoginMenu={handleLoginMenu}
            openLoginMenu={openLoginMenu}
            handleClose={handleClose}
            anchorLoginMenu={anchorLoginMenu}
            doLoginLogout={doLoginLogout}
          />
        </div>
      </Toolbar>
    </AppBar>
  );
};

export const Header = withRouter(HeaderInner);
