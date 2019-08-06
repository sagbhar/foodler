import React, { Component } from "react";
import { connect } from "react-redux";
import { PropTypes } from "prop-types";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import ReactDOM from "react-dom";
import Alert from "react-bootstrap/Alert";
import Cookies from "universal-cookie";
import { loginUser } from "../actions/authActions";

import "../login.css";

export class Login extends Component {
  constructor(props) {
    super(props);
    //this.handleDismiss = this.handleDismiss.bind(this);
    this.state = {
      justClicked: true,
      validated: false,
      error: "",
      message: "",
      data: "",
      show: true,
      access_token: "",
      email: "",
      password: ""
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this); 
  }
  componentDidMount() {
    if (this.props.auth.isAuthenticated) {
      this.props.history.push("/catalog");
    }
  }
  componentWillReceiveProps(nextProps) {
    if (nextProps.auth.isAuthenticated) {
      this.props.history.push("/catalog");
    }
  }
  handleChange(e) {
      this.setState({[e.target.name]: e.target.value})
  }

  handleSubmit(event) {
      event.preventDefault();
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }
    this.state.validated = true;
    const formData = new URLSearchParams();
    formData.append("grant_type", "password");
    formData.append("username", this.state.email);
    formData.append("password", this.state.password);
    
      this.props.loginUser(formData);
   
    
    
  }

  render() {
    //   const { error } = this.props.errors;
      const { email, password } = this.state;
    //   if(error) {
    //       this.state.validated = false;
    //   }
     
      return (
          
      <div className="loginFormContainer">
        <Form noValidate validated={this.state.validated} className="loginForm" onSubmit = {this.handleSubmit}>
          <Form.Group controlId="formBasicEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control  name="email" type="email"  placeholder="Enter email" required value={email} onChange={this.handleChange}/>
            <Form.Control.Feedback type="invalid">
              Please enter valid email address
            </Form.Control.Feedback>
          </Form.Group>

          <Form.Group controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control  name="password" type="password"  placeholder="Password" required value={password} onChange={this.handleChange}/>
            <Form.Control.Feedback type="invalid">
              Please enter correct password
            </Form.Control.Feedback>
          </Form.Group>
          <Button variant="primary" type="submit">
            Submit
          </Button>
        </Form>
      </div>
    );
  }
}
Login.propTypes = {
  loginUser: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  auth: state.auth,
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { loginUser }
)(Login);
