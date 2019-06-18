import React, { Component } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import ReactDOM from 'react-dom';
import Alert from 'react-bootstrap/Alert';
import desktopImage from './homepage.jpg';
import mobileImage from './homepage.jpg';
import Logo from './logo1.png';
import Image from 'react-bootstrap/Image';
import { instanceOf } from 'prop-types';
import { Cookies } from 'react-cookie';

export class Login extends Component {
    static propTypes = {
        cookies: instanceOf(Cookies)
      };
    constructor(props) {
        super(props);
        
        this.state = {
            justClicked: true,
            validated: false,
            error:'',
            message:'',
            data:'',
            show: true
        };
      }
    
     
      handleDismiss(event){
        this.setState({ show: false });
      }
      handleTokenGenerate(){
        const formData = new URLSearchParams();

        formData.append('grant_type', 'password');
        formData.append('username', this.refs.emailId.value);
        formData.append('password', this.refs.password.value);
        
        let headers = new Headers();
      
        headers.append('Accept','application/json');
        headers.append('Content-Type','application/x-www-form-urlencoded');
        headers.append('Authorization',  'Basic aGVuZGktY2xpZW50OmhlbmRpLXNlY3JldA==');
          
          fetch('http://localhost:8765/auth/token', {
              method: 'POST',
              credentials: 'include',
              headers: headers,
              body: formData,
          })
              .then(res => {
                  return res.json();
              })
              .then((data) => {
                  if (typeof data.access_token !== 'undefined') {
                      const { cookies } = this.props;
                      cookies.set("AccessToken", data.access_token, { httpOnly: true });
                  } else {
                      this.setState({ message: "Login Failed" });
                      this.setState({ error: true });
                  }
              })
              .catch((error) => {
                  console.error("ErrorData" + error);
              });
      }
        
      handleSubmit(event) {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        this.setState({ validated: true });
        this.setState({ show: true });
        
       if (ReactDOM.findDOMNode(this.refs.emailId).value !== '' &&
            ReactDOM.findDOMNode(this.refs.password).value !== '') {
                this.handleTokenGenerate();
        }
      }

    render() { 
        const { validated } = this.state;
        const { error } = this.state;
        const { message } = this.state;
        const { show } = this.state;
        const imageUrl = window.innerWidth >= 650 ? desktopImage : mobileImage;
        let errorDisplay;
        if(error) {
            errorDisplay =   <Alert onClose={(e) => this.handleDismiss(e)} dismissible variant='danger'>
                                <Alert.Heading>Oh snap! You got an error!</Alert.Heading>
                                {error}{message}
                            </Alert>
        }
        return (<div className="App" style={{backgroundImage: `url(${imageUrl})` }}>
        
        <div className="App-content">
        <div className="fixed-top">
            <Image src={Logo} rounded thumbnail='true' width='70' height='40'/>
        </div>
            <div className="container border border-light p-3 mb-2 bg-light text-dark">
            {errorDisplay}
            <h2>Login Page</h2>
            <Form noValidate
                validated={validated}
            >

                <Form.Group as={Col} controlId="formEmail">
                    <Form.Label>Email Address</Form.Label>
                    <Form.Control type='Email' placeholder="Email Address" ref ='emailId' required/>
                    <Form.Control.Feedback type="invalid">
                        Please enter the email address.
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group as={Col} controlId="formPassword">
                <Form.Label>Password</Form.Label>
                    <Form.Control type='Password' placeholder="Password" ref ='password' required/>
                    <Form.Control.Feedback type="invalid">
                        Please enter the password.
                    </Form.Control.Feedback>
                </Form.Group>
                
                <Button variant="outline-warning" type="button" onClick={e => this.handleSubmit(e)}
                >
                    Submit
                </Button>
            </Form>
            
        </div></div></div> );
        
    }
}

export default Login;