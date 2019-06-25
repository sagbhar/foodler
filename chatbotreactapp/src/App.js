import React, { Component } from "react";
import { Route, Link, BrowserRouter as Router } from 'react-router-dom';
import { Landing } from './components/Landing';
import Registration from './components/Registration';
import Login from './components/Login';
import "./App.css";
import { CookiesProvider } from "react-cookie";
import Header from './components/Header';
import VendorList from './components/VendorList';

class App extends Component {
  render() {
    return (
      <CookiesProvider>
        <Router>
          <div className="app">
            <Header/>
            <div className="app-container">
              <Route exact path="/" component={Landing} />
              <Route exact path="/register" component={Registration} />
              <Route exact path="/login" component={Login} />
              <Route exact path="/vendorList" component={VendorList} />
            </div>
          </div>
        </Router>
      </CookiesProvider>
    );
  }
}

export default App;
