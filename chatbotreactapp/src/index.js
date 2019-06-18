import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';
import * as serviceWorker from './serviceWorker';
import { Route, Link, BrowserRouter as Router } from 'react-router-dom';
import { HomePage } from './HomePage';
import UserRegistrationForm from './UserRegistrationForm';
import Login from './Login';

const routing = (
    <Router>
      <div>
        <Route path="/home" component={HomePage} />
        <Route path="/register" component={UserRegistrationForm} />
        <Route path="/login" component={Login} />
      </div>
    </Router>
  )

ReactDOM.render(routing, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
