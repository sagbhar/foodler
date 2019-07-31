import React, { Component } from 'react';
import { connect } from 'react-redux';
import { PropTypes } from 'prop-types';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import ReactDOM from 'react-dom';
import Alert from 'react-bootstrap/Alert';
import Cookies from 'universal-cookie';
import { loginUser } from '../actions/authActions';

export class Login extends Component {
    
    constructor(props) {
        super(props);
        this.handleDismiss = this.handleDismiss.bind(this);
        this.state = {
            justClicked: true,
            validated: false,
            error:'',
            message:'',
            data:'',
            show: true,
            access_token:''
        };
      }
      componentDidMount() {
          if(this.props.auth.isAuthenticated) {
              this.props.history.push('/catalog');
          }
      }
      componentWillReceiveProps(nextProps) {
          if(nextProps.auth.isAuthenticated) {
              this.props.history.push('/catalog');
          }
      }
    
      handleDismiss(event){
        this.setState({ show: false });
      }
     

      handleSubmit(event) {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        this.setState({ validated: true });
        this.setState({ show: true });
        const formData = new URLSearchParams();
      
        formData.append('grant_type', 'password');
        formData.append('username', this.refs.emailId.value);
        formData.append('password', this.refs.password.value);
        
       if (ReactDOM.findDOMNode(this.refs.emailId).value !== '' &&
            ReactDOM.findDOMNode(this.refs.password).value !== '') {
                this.props.loginUser(formData);
        }
      }

    render() { 
        const { validated } = this.state;
        const { error } = this.state;
        const { message } = this.state;
        const { show } = this.state;
       
        let errorDisplay;
        if(""!==error && 'undefined'!==typeof error && show) {
            errorDisplay =   <Alert onClose={(e) => this.handleDismiss(e)} dismissible variant='danger'>
                                <Alert.Heading>Oh snap! You got an error!</Alert.Heading>
                                {error}{message}
                            </Alert>
        }
        return (
            <div className="container">
            {errorDisplay}
            <div className="border border-light p-3 mb-2 bg-light text-dark">
            <h2>Login Page</h2>
            <Form noValidate
                validated={validated}>

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
            
        </div></div> );
        
    }
}
Login.propTypes = {
    loginUser: PropTypes.func.isRequired
}

const mapStateToProps = state => ({
    auth: state.auth
})

export default connect(mapStateToProps, { loginUser })(Login);