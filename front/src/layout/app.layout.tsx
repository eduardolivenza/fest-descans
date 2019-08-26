import * as React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import { SessionContext, routesLinks } from "core";
import { LoginMenu } from "./loginMenu.layout";
import { MainMenu } from "./mainMenu.layout";
import { withRouter, RouteComponentProps } from "react-router-dom";

//const logo = require("./../images/logo.jpg");
const logo = require("./../images/FEST_COLOR_3.png");

const growStyle = {
  flexGrow: 1,
};

interface Props extends RouteComponentProps { }
const AppLayoutInner: React.FunctionComponent<Props> = (props) => {

  const { history } = props;

  const [anchorEl, setanchorEl] = React.useState(null);
  const openMenu = Boolean(anchorEl);

  const [anchorLoginMenu, setanchorLoginMenu] = React.useState(null);
  const openLoginMenu: boolean = Boolean(anchorLoginMenu);

  const handleClose = () => {
    setanchorEl(null);
    setanchorLoginMenu(null);
  }

  const handleMenu = (event: React.MouseEvent<HTMLElement>) => {
    setanchorEl(event.currentTarget);
  }

  const handleLoginMenu = (event: React.MouseEvent<HTMLElement>) => {
    setanchorLoginMenu(event.currentTarget);
  }

  const goToDefault = () => {
    setanchorEl(null);
    history.push(routesLinks.default);
  }

  const doLoginLogout = () => {
    setanchorEl(null);
    history.push(routesLinks.login);
  }

  const goToProductsList = () => {
    setanchorEl(null);
    history.push(routesLinks.productCollection);
  }

  const loginContext = React.useContext(SessionContext);

  return (
    <div >
      <AppBar position="static"  >
        <Toolbar variant="dense">
          <MainMenu
            handleMenu={handleMenu}
            openMenu={openMenu}
            handleClose={handleClose}
            anchorMenu={anchorEl}
            goToDefault={goToDefault}
            goToProductsList={goToProductsList} />
          <img src={logo} alt="Logo" height="100" />;

          <Typography style={growStyle} variant="h6" color="inherit" >
            {loginContext.login}
          </Typography>
          <div>
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
      {props.children}
    </div>
  );

};

export const AppLayout = withRouter(AppLayoutInner);