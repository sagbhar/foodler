import React, { Component } from 'react';
import desktopImage from '../img/landingImage.jpg';
import mobileImage from '../img/landingImage.jpg';
import '../landing.css';
import Button from 'react-bootstrap/Button'


export class Landing extends Component {
    constructor(props) {
        super(props);
        this.loadLoginPage = this.loadLoginPage.bind(this);
        this.loadRegistrationPage = this.loadRegistrationPage.bind(this);
    }

    loadLoginPage(){
        this.props.history.push('/login');
    }
    loadRegistrationPage(){
        this.props.history.push('/register');
    }

    render() { 
        const imageUrl = window.innerWidth >= 650 ? desktopImage : mobileImage;
        return(
            <div className="App" style={{backgroundImage: `url(${imageUrl})` }}>
                <div className="App-content">
                    <Button variant="outline-warning" size="lg" href="#" onClick={this.loadLoginPage} block>
                        Login
                    </Button>
                    <Button variant="outline-warning" size="lg" href="#" onClick={this.loadRegistrationPage} block>
                        Register
                    </Button>
                </div>
            </div>
        );
    }
}
export default Landing;