import * as React from "react";
import * as ReactDOM from "react-dom";
import { HashRouter, Switch, Route } from "react-router-dom";
import { LoginPage, ProductCollectionPage, RegisterPage, DefaultPage } from "./scenes";
import { routerSwitchRoutes, SessionContext, getSessionCookie } from "core";
import { ProductViewPage } from "scenes";

const Routes = () => {

  const [session, setSession] = React.useState(getSessionCookie());
  React.useEffect(
    () => {
      setSession(getSessionCookie());
    },
    [session]
  );

  return (
    <SessionContext.Provider value={session}>
      <HashRouter>
        <Switch>
          <Route
            exact={true}
            path={routerSwitchRoutes.login}
            component={LoginPage}
          />
          <Route
            exact={true}
            path={routerSwitchRoutes.register}
            component={RegisterPage}
          />
          <Route
            exact={true}
            path={routerSwitchRoutes.default}
            component={DefaultPage}
          />
          <Route
            path={routerSwitchRoutes.productCollection}
            component={ProductCollectionPage}
          />
          <Route
            path={routerSwitchRoutes.productView}
            component={ProductViewPage}
          />
        </Switch>
      </HashRouter>
    </SessionContext.Provider>);
};

const App = () => (
  <div className="App">
    <Routes />
  </div>
);

ReactDOM.render(<App />, document.getElementById("root"));