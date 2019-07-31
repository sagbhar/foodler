import React, { Component } from "react";
import { Route, Link, BrowserRouter as Router, Switch} from "react-router-dom";
import { Landing } from "./components/Landing";
import Registration from "./components/Registration";
import Login from "./components/Login";
import "./App.css";
import { CookiesProvider } from "react-cookie";
import { Provider } from "react-redux";
import store from './store';
import PrivateRoute from './components/PrivateRoute';
import Header from "./components/Header";
import VendorList from "./components/VendorList";
import Restaurant from "./components/Restaurant";
import OrderReview from "./components/OrderReview";

class App extends Component {
  render() {
    return (
      <CookiesProvider>
        <Provider store={store}>
          <Router>
            <div className="app">
              <Header />
              <div className="app-container">
                <Route exact path="/" component={Landing} />
                <Route exact path="/register" component={Registration} />
                <Route exact path="/login" component={Login} />
                <Switch>
                <PrivateRoute exact path="/catalog" component={VendorList} />
                </Switch>
                <Switch>
                <PrivateRoute exact path="/restaurant/:id" component={Restaurant} />
                </Switch>
                <Switch>
                <PrivateRoute
                  exact
                  path="/orderReview/:orderId"
                  component={OrderReview}
                />
                </Switch>
              </div>
            </div>
          </Router>
        </Provider>
      </CookiesProvider>
    );
  }
}

export default App;
