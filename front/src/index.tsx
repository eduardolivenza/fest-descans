import React, { Suspense } from 'react';
import * as ReactDOM from "react-dom";
import { HashRouter, Switch, Route } from "react-router-dom";
import { routerSwitchRoutes, SessionContext, getSessionCookie } from "core";
import { LoginPage, ProductCollectionPage, RegisterPage, LandingPage, ProductViewPage, SuppliersCollectionPage, ProductEditPage, SupplierEditPage } from "scenes";
import 'config/i18n/i18n';
import { rootReducer } from 'store/rootReducer';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware, compose } from 'redux'
import createSagaMiddleware from 'redux-saga'
import { mainSaga } from 'store/sagas';
import { fetchedInitialDataAction } from 'pods/product-collection';

const composeEnhancer = window['__REDUX_DEVTOOLS_EXTENSION_COMPOSE__'] || compose;
const sagaMiddleware = createSagaMiddleware();

const store = createStore(
  rootReducer(),
  composeEnhancer(applyMiddleware(sagaMiddleware))
)
sagaMiddleware.run(mainSaga)
store.dispatch(fetchedInitialDataAction());

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
                        component={LandingPage}
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
                    <Route
                        path={routerSwitchRoutes.supplierAdd}
                        component={SupplierEditPage}
                    />
                    <Route
                        path={routerSwitchRoutes.supplierEdit}
                        component={SupplierEditPage}
                    />
                </Switch>
            </ HashRouter>
        </ SessionContext.Provider>

    );
};

const App = () => (
    <div className="App" >
        <Suspense fallback={(<div>Loading application...</div>)}>
            < Routes />
        </Suspense>
    </div>
)


ReactDOM.render(<Provider store={store}><App /></Provider>, document.getElementById("root"));