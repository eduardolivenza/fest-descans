import React from "react";
import classNames from "classnames";
import PropTypes from "prop-types";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import styles from "common/styles/jss/material-kit-react/components/headerStyle.js";
import { LanguageMenu } from "layout/languageMenu.layout";
import { LoginMenu } from "layout/loginMenu.layout";
import { useTranslation } from "react-i18next";
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';

const logo = require("public/images/FEST_COLOR_3.png");
const useStyles = makeStyles(styles);

export function Header(props) {
  const classes = useStyles({});

  const [anchorLanguageMenu, setAnchorLanguageMenu] = React.useState(null);
  const openLanguageMenu: boolean = Boolean(anchorLanguageMenu);
  const [anchorLoginMenu, setanchorLoginMenu] = React.useState(null);
  const openLoginMenu: boolean = Boolean(anchorLoginMenu);

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
  };

  const handleLoginMenu = (event: React.MouseEvent<HTMLElement>) => {
    setanchorLoginMenu(event.currentTarget);
  };

  const { t, i18n } = useTranslation();

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
    //Cookies.remove("session");
    //history.push("/login");
  };

  const { color,  fixed, absolute } = props;
  const appBarClasses = classNames({
    [classes.appBar]: true,
    [classes[color]]: color,
    [classes.absolute]: absolute,
    [classes.fixed]: fixed
  });

  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <AppBar className={appBarClasses}>
      <Toolbar className={classes.container}>
        <img src={logo} alt="Logo" height="100vh" />

        <div className={classes.rightIcons}>
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
}

Header.defaultProp = {
  color: "white"
};

Header.propTypes = {
  color: PropTypes.oneOf([
    "primary",
    "info",
    "success",
    "warning",
    "danger",
    "transparent",
    "white",
    "rose",
    "dark"
  ]),
  brand: PropTypes.string,
  fixed: PropTypes.bool,
  absolute: PropTypes.bool,
  // this will cause the sidebar to change the color from
  // props.color (see above) to changeColorOnScroll.color
  // when the window.pageYOffset is heigher or equal to
  // changeColorOnScroll.height and then when it is smaller than
  // changeColorOnScroll.height change it back to
  // props.color (see above)
  changeColorOnScroll: PropTypes.shape({
    height: PropTypes.number.isRequired,
    color: PropTypes.oneOf([
      "primary",
      "info",
      "success",
      "warning",
      "danger",
      "transparent",
      "white",
      "rose",
      "dark"
    ]).isRequired
  })
};
