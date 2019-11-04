import React, { Component } from "react";
import { connect } from 'react-redux';
import {Link} from 'react-router-dom';
import PropTypes from 'prop-types';
import {Navbar, Nav, Image} from 'react-bootstrap';
import Logo from '../img/Dinners.png';
import { logoutUser } from '../actions/authActions';
import '../header.css';
class Header extends Component {
  onLogoutClick(e) {
    e.preventDefault();
    this.props.logoutUser();
  }
  render() {
    const { isAuthenticated, user } = this.props.auth;
    const foodlerIconRoute = isAuthenticated ? '/catalog' : '/';

    const authLinks = (
      <Nav className="">
        <div className="logoutDiv">
        <Nav.Link href="" className="logoutLink" onClick={this.onLogoutClick.bind(this)}>Logout</Nav.Link>
        </div>
      </Nav>
     
    );
    const guestLinks = (
      <Nav className="mr-auto">
      <Nav.Link href="#home">Dummy Link1</Nav.Link>
      <Nav.Link href="#link">Dummy Link2</Nav.Link>
      </Nav>
    );
    return (
      <Navbar  expand="lg" fixed="top">
         <Link to={ foodlerIconRoute }><Image src={Logo} rounded thumbnail='true' width='70' height='40' className="site-icon"/></Link>
         
        {/* <Navbar.Brand href="#home">Foodler</Navbar.Brand> */}
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
         
            {isAuthenticated ? authLinks: guestLinks}
      
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
