import React, { Suspense } from 'react';
import * as ReactDOM from "react-dom";
import { HashRouter, Switch, Route } from "react-router-dom";
import { routerSwitchRoutes, SessionContext, getSessionCookie } from "core";
import { LoginPage, ProductCollectionPage, RegisterPage, DefaultPage, ProductViewPage, SuppliersCollectionPage, ProductEditPage } from "scenes";
import 'config/i18n/i18n';


const Routes = () => {

    const [session, setSession] = React.useState(getSessionCookie());
    React.useEffect(
        () => {
            setSession(getSessionCookie());
        },
        [session]
    );

    return (
        <SessionContext.Provider value={session} >
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
                    <Route
                        path={routerSwitchRoutes.productAdd}
                        component={ProductEditPage}
                    />
                    <Route
                        path={routerSwitchRoutes.productEdit}
                        component={ProductEditPage}
                    />
                    <Route
                        path={routerSwitchRoutes.suppliersCollection}
                        component={SuppliersCollectionPage}
                    />
                </Switch>
            </ HashRouter>
        </ SessionContext.Provider>

    );
};

const App = () => (
    <div className="App" >
        <Suspense fallback={(<div>Loading </div>)}>
            < Routes />
        </Suspense>
    </div>
)


ReactDOM.render(<App />, document.getElementById("root"));