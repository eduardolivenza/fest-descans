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
import SwipeableDrawer from "@material-ui/core/SwipeableDrawer";
import Divider from "@material-ui/core/Divider";
import MenuIcon from "@material-ui/icons/Menu";
import IconButton from "@material-ui/core/IconButton";
import { MainMenu } from "./menus/mainMenu.layout";

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
  const [state, setState] = React.useState({
    left: false,
    right: false
  });

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

  const goToLandingPage = () => {
    history.push(routesLinks.default);
  };

  const goToProductsList = () => {
    history.push(routesLinks.productCollection);
  };

  const goToSuppliersPage = () => {
    history.push(routesLinks.suppliersCollection);
  };

  const toggleDrawer = (side, open) => event => {
    if (
      event &&
      event.type === "keydown" &&
      (event.key === "Tab" || event.key === "Shift")
    ) {
      return;
    }
    setState({ ...state, [side]: open });
  };

  const sideList = side => (
    <div
      className={classes.sideMenu}
      role="presentation"
      onClick={toggleDrawer(side, false)}
      onKeyDown={toggleDrawer(side, false)}
    >
      <Divider />
      <MainMenu
        goToDefault={goToLandingPage}
        goToProductsList={goToProductsList}
        goToSuppliersPage={goToSuppliersPage}
      />
      <Divider />
    </div>
  );

  return (
    <>
      <AppBar className={appBarClasses}>
        <Toolbar className={classes.toolBarContainer}>
          <div className={classes.toolBarIcons}>
            <IconButton
              edge="start"
              color="inherit"
              aria-label="open drawer"
              onClick={toggleDrawer("left", true)}
            >
              <MenuIcon />
            </IconButton>
            <img src={logo} alt="Logo" height="100vh" />
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
      <SwipeableDrawer
        open={state.left}
        onClose={toggleDrawer("left", false)}
        onOpen={toggleDrawer("left", true)}
      >
        {sideList("left")}
      </SwipeableDrawer>
    </>
  );
};

export const Header = withRouter(HeaderInner);
