import React, { Component } from 'react';
import './App.css';


import { HomePage } from './HomePage';
import { CookiesProvider } from 'react-cookie';

class App extends Component {

  render() {
    return (  
      <CookiesProvider>
        <HomePage />
      </CookiesProvider>
    );
  }
}

export default App;
