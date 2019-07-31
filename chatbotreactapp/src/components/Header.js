import React, { Component } from "react";
import { connect } from 'react-redux';
import {Link} from 'react-router-dom';
import PropTypes from 'prop-types';
import {Navbar, Nav, Image} from 'react-bootstrap';
import Logo from '../logo1.png';
import { logoutUser } from '../actions/authActions';
import '../header.css';
class Header extends Component {
  onLogoutClick(e) {
    e.preventDefault();
    this.props.logoutUser();
  }
  render() {
    const { isAuthenticated, user } = this.props.auth;

    const authLinks = (
      <Nav className="mr-auto">
        <Nav.Link href="" onClick={this.onLogoutClick.bind(this)}>Logout</Nav.Link>
      </Nav>
     
    );
    const guestLinks = (
      <Nav className="mr-auto">
      <Nav.Link href="#home">Dummy Link1</Nav.Link>
      <Nav.Link href="#link">Dummy Link2</Nav.Link>
      </Nav>
    );
    return (
      <Navbar bg="light" expand="lg" fixed="top">
         <Link to="/"><Image src={Logo} rounded thumbnail='true' width='70' height='40'/></Link>
        {/* <Navbar.Brand href="#home">Foodler</Navbar.Brand> */}
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            {isAuthenticated ? authLinks: guestLinks}
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    );
  }
}
Header.propTypes = {
  logoutUser: PropTypes.func.isRequired
}
const mapStateToProps = state => ({
  auth: state.auth
})
export default connect(mapStateToProps, { logoutUser })(Header)
