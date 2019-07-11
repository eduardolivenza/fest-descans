import * as React from "react";
import * as ReactDOM from "react-dom";
import { HashRouter, Switch, Route } from "react-router-dom";
import { LoginPage, ProductCollectionPage, RegisterPage, DefaultPage } from "./scenes";
import { routerSwitchRoutes, SessionProvider } from "core";
import { HotelEditPage } from "scenes";

ReactDOM.render(
  <SessionProvider>
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
          path={routerSwitchRoutes.hotelEdit}
          component={HotelEditPage}
        />
      </Switch>
    </HashRouter>
  </SessionProvider>,
  document.getElementById("root")
);
