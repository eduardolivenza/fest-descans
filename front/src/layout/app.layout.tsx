import * as React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import { SessionContext, routesLinks } from "core";
import { withRouter, RouteComponentProps } from "react-router-dom";
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Drawer from '@material-ui/core/Drawer';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import Container from '@material-ui/core/Container';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import NotificationsIcon from '@material-ui/icons/Notifications';
import { Copyright } from "./copyright";
import { MainMenu } from "./mainMenu.layout";
import { LoginMenu } from "./loginMenu.layout";
import { LanguageMenu} from "./languageMenu.layout";
import Cookies from "js-cookie";
import { useTranslation } from 'react-i18next';


const logo = require("public/images/FEST_COLOR_3.png");
const drawerWidth = 25;

const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
  },
  toolbar: {
    paddingRight: '3vh', // keep right padding when drawer closed
  },
  toolbarIcon: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: '0 8px',
    ...theme.mixins.toolbar,
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}vh)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  menuButton: {
    marginRight: 36,
  },
  menuButtonHidden: {
    display: 'none',
  },
  title: {
    flexGrow: 1,
  },
  login: {
    marginRight: theme.spacing(4),
  },
  drawerPaper: {
    position: 'relative',
    whiteSpace: 'nowrap',
    width: `${drawerWidth}vh`,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerPaperClose: {
    overflowX: 'hidden',
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    width: theme.spacing(7),
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9),
    },
  },
  appBarSpacer: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    height: '100vh',
    overflow: 'auto',
  },
  container: {
    paddingTop: theme.spacing(6),
    paddingBottom: theme.spacing(4),
  },
  paper: {
    padding: theme.spacing(2),
    display: 'flex',
    overflow: 'auto',
    flexDirection: 'column',
  },
}));

interface Props extends RouteComponentProps {
  
 }

const AppLayoutInner: React.FunctionComponent<Props> = (props) => {

  const { history } = props;

  const classes = useStyles({});

  const [open, setOpen] = React.useState(false);
  
  const [anchorLoginMenu, setanchorLoginMenu] = React.useState(null);
  const openLoginMenu: boolean = Boolean(anchorLoginMenu);

  const [anchorLanguageMenu, setAnchorLanguageMenu] = React.useState(null);
  const openLanguageMenu: boolean = Boolean(anchorLanguageMenu);

  const handleClose = () => {
    setanchorLoginMenu(null);
    setAnchorLanguageMenu(null);
  }

  const handleLoginMenu = (event: React.MouseEvent<HTMLElement>) => {
    setanchorLoginMenu(event.currentTarget);
  }

  const handleLanguageMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorLanguageMenu(event.currentTarget);
  }

  const doLoginLogout = () => {
    Cookies.remove("session");
    history.push("/login");
  };

  const loginContext = React.useContext(SessionContext);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  const goToDefault = () => {
    history.push(routesLinks.default);
  }

  const goToProductsList = () => {
    history.push(routesLinks.productCollection);
  }

  const goToSuppliersPage = ()=> {
    history.push(routesLinks.suppliersCollection);
  }

  const { t, i18n } = useTranslation();

  const changeLanguage = lng => {
      i18n.changeLanguage(lng);
    };

  return (
    <div className={classes.root}>
      <CssBaseline />
    
      <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}>
        <Toolbar className={classes.toolbar}>
          <IconButton
            edge="start"
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            className={clsx(classes.menuButton, open && classes.menuButtonHidden)}>
            <MenuIcon />
          </IconButton>
          <img src={logo} alt="Logo" height="100vh" />
          <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title} />

          <Typography className = {classes.login} variant="h6" color="inherit" >
            {loginContext.email}
          </Typography>
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
          <IconButton color="inherit">
            <Badge badgeContent={4} color="secondary">
              <NotificationsIcon />
            </Badge>
          </IconButton>
        </Toolbar>
      </AppBar>
     
      <Drawer
        variant="permanent"
        classes={{
          paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
        }}
        open={open} >
        <div className={classes.toolbarIcon}>
          <IconButton onClick={handleDrawerClose}>
            <ChevronLeftIcon />
          </IconButton>
        </div>
        <div style={{ paddingTop: '35px' }}>
          <Divider />
          <MainMenu goToDefault={goToDefault} goToProductsList={goToProductsList} goToSuppliersPage={goToSuppliersPage} />
          <Divider />
        </div>
      </Drawer>
      <main className={classes.content}>
        <div className={classes.appBarSpacer} />
        <Container maxWidth="xl" className={classes.container}>
          {props.children}
        </Container>
        <Copyright />
      </main>
    </div>
  );
}

export const AppLayout = withRouter(AppLayoutInner);