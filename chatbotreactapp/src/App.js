import React, { Component } from "react";
import { Route, Link, BrowserRouter as Router } from 'react-router-dom';
import { Landing } from './components/Landing';
import Registration from './components/Registration';
import Login from './components/Login';
import "./App.css";
import { CookiesProvider } from "react-cookie";

class App extends Component {
  render() {
    return (
      <CookiesProvider>
        <Router>
          <div className="app">
            <Route exact path="/" component={Landing} />
            <div className="app-container">
              <Route exact path="/register" component={Registration} />
              <Route exact path="/login" component={Login} />
            </div>
          </div>
        </Router>
      </CookiesProvider>
    );
  }
}

export default App;
