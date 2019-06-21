import React, { Component } from "react";
import {Link} from 'react-router-dom';
import {Navbar, Nav, Image} from 'react-bootstrap';
import Logo from '../logo1.png';
import '../header.css';
export default class Header extends Component {
  render() {
    return (
      <Navbar bg="light" expand="lg" fixed="top">
         <Link to="/"><Image src={Logo} rounded thumbnail='true' width='70' height='40'/></Link>
        {/* <Navbar.Brand href="#home">Foodler</Navbar.Brand> */}
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="#home">Home</Nav.Link>
            <Nav.Link href="#link">Link</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    );
  }
}
