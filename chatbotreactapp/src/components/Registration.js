import React, { Component } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import ReactDOM from 'react-dom';
import Alert from 'react-bootstrap/Alert'

export class Registration extends Component {
    constructor(props) {
        super(props);
        this.handleUserTypeSelection = this.handleUserTypeSelection.bind(this);
        this.handleDismiss = this.handleDismiss.bind(this);
        this.formValidation = this.formValidation.bind(this);
        this.state = {
            justClicked: true,
            validated: false,
            error:'',
            message:'',
            data:'',
            show: true
        };
      }
    
      handleUserTypeSelection = () => {
          if(ReactDOM.findDOMNode(this.refs.selectedUserType).value==='1' || ReactDOM.findDOMNode(this.refs.selectedUserType).value==='') {
            this.setState({ justClicked: true });
          }else {
            this.setState({ justClicked: false });
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
        
       if(this.formValidation()) {
            fetch('http://localhost:8765/register/register', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "firstName": this.refs.firstName.value,
                "lastName": this.refs.lastName.value,
                "mobNum": this.refs.mob.value,
                "emailId": this.refs.emailId.value,
                "password": this.refs.password.value,
                "userType": this.refs.selectedUserType.value,
                "shopName": this.refs.selectedshopName.value

            }),
            })  
                .then((res) => {
                    return res.json();
                })
                .then((data) => {
                    if(typeof data.status==='undefined'){
                        this.props.history.push('/login');
                    }else{
                        this.setState({ message: data.message });
                        this.setState({ error: data.error });
                        event.preventDefault();
                    }
                    //console.log(data);
                })
                .catch((error) =>{
                    console.error("ErrorData"+error)
                });
        }
      }

    formValidation() {
        if (ReactDOM.findDOMNode(this.refs.selectedUserType).value !== '' &&
            ReactDOM.findDOMNode(this.refs.firstName).value !== '' &&
            ReactDOM.findDOMNode(this.refs.lastName).value !== '' &&
            ReactDOM.findDOMNode(this.refs.mob).value !== '' &&
            ReactDOM.findDOMNode(this.refs.emailId).value !== '' &&
            ReactDOM.findDOMNode(this.refs.password).value !== '') {
            if (ReactDOM.findDOMNode(this.refs.selectedUserType).value === '2' && ReactDOM.findDOMNode(this.refs.selectedshopName).value !== '') {
                return true;
            }
            else if (ReactDOM.findDOMNode(this.refs.selectedUserType).value === '1'){
                return true;
            }
        }
        return false;
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
                                {error} Caused By {message}
                            </Alert>
        }
        return (<div className="container">
            {errorDisplay}
            <h2>User Registration Form</h2>
            <Form noValidate
                validated={validated}>
                <Form.Group as={Col} controlId="formFirstName">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control placeholder="First Name" ref ='firstName' required/>
                    <Form.Control.Feedback type="invalid">
                        Please enter the first Name.
                    </Form.Control.Feedback>
                </Form.Group>
            
                <Form.Group as={Col} controlId="formLastName">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control placeholder="Last Name" ref ='lastName' required/>
                    <Form.Control.Feedback type="invalid">
                        Please enter the last Name.
                    </Form.Control.Feedback>
                </Form.Group>
                
                <Form.Group as={Col} controlId="formMobileNumber">
                    <Form.Label>Mobile Number</Form.Label>
                    <Form.Control placeholder="Mobile Number" ref ='mob' required/>
                    <Form.Control.Feedback type="invalid">
                        Please enter the mobile Number.
                    </Form.Control.Feedback>
                </Form.Group>

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
                
                <Form.Group as={Col} controlId="formUserType">
                    <Form.Label>User Type</Form.Label>
                    <Form.Control as="select" ref ='selectedUserType' onChange={this.handleUserTypeSelection} required>
                        <option value="">Select</option>
                        <option value="1">Employee</option>
                        <option value="2">Vendor</option>
                    </Form.Control>
                    <Form.Control.Feedback type="invalid">
                        Please select the User Type.
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group as={Col} controlId="formShopName">
                    <Form.Label>Shop Name</Form.Label>
                    <Form.Control disabled={this.state.justClicked} ref ='selectedshopName' placeholder="Shop Name" required/>
                    <Form.Control.Feedback type="invalid">
                        Please enter the Shop Name.
                    </Form.Control.Feedback>
                </Form.Group>

                <Button variant="primary" type="button" onClick={e => this.handleSubmit(e)}
                >
                    Submit
                </Button>
            </Form>
            
        </div> );
        
    }
}

export default Registration;