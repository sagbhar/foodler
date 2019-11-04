import React, { Component } from "react";
import { Route, Link, BrowserRouter as Router, Switch} from "react-router-dom";
import { Landing } from "./components/Landing";
import Registration from "./components/Registration";
import Login from "./components/Login";
import "./App.css";
import { CookiesProvider, Cookies } from "react-cookie";
import { Provider } from "react-redux";
import store from './store';
import PrivateRoute from './components/PrivateRoute';
import Header from "./components/Header";
import VendorList from "./components/VendorList";
import Restaurant from "./components/Restaurant";
import OrderReview from "./components/OrderReview";
import Coookies from 'universal-cookie';
import jwt_decode from 'jwt-decode';
import { setCurrentUser, logoutUser } from './actions/authActions';
const cookies = new Cookies();
const accessToken = cookies.get('AccessToken');
if(accessToken) {
  // Set auth token header auth
  //setAuthToken(accessToken);
  // Decode token and get user info and expiration
  const decoded = jwt_decode(accessToken);
  // Set user and isAuthenticated
  store.dispatch(setCurrentUser(decoded));

  // Check for expired token 
  const currentTime = Date.now() / 1000;
  if(decoded.exp < currentTime) {
    // Logout User
    store.dispatch(logoutUser());
   

    // Redirect to login
    window.location.href = '/login';
  }
}


class App extends Component {
  render() {
    return (
      <CookiesProvider>
        <Provider store={store}>
          <Router>
            <div className="app">
             
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
