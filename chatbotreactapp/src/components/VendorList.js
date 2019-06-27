import React, { Component } from 'react';
import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import { Link } from 'react-router-dom';
import '../vendorList.css';
import Cookies from 'universal-cookie';
export default class VendorList extends Component {

  constructor(props) {
    super(props);
    this.generateCatalog = this.generateCatalog.bind(this);
    this.state = {
        restaurantDetails:[]
    };
  }
  componentWillMount(){
    this.generateCatalog();
  }

  generateCatalog(){
  let headers = new Headers();
  const cookies = new Cookies(); 
  headers.append('Accept','application/json');
  headers.append('Content-Type','application/x-www-form-urlencoded');
  headers.append('Authorization',  'Bearer '+ cookies.get('AccessToken'));

  fetch('http://localhost:8765/catalog/getAllRestaurants', {
    method: 'GET',
    headers: headers,
})
    .then(res => { 
        return res.json();
    })
    .then((data) => {
        if(data.error==='invalid_token'){
          this.props.history.push('/login');
        }else {
          this.setState({restaurantDetails: data});
        }
    })
    .catch((error) => {
        console.error("ErrorData" + error);
    });
  }
    render() {
      let restaurantDetails = this.state.restaurantDetails;
      let restaurants = [];
      restaurantDetails.map((restaurant) => {
        let a = "/restaurant/"+restaurant.shopId;
        restaurants.push(<Col ><Link to={a} ><Card style={{ width: '18rem' }}>
        <Card.Img variant="top" src={restaurant.imageURL} />
        <Card.Body>
          <Card.Title >{restaurant.restaurantName}</Card.Title>
          <Card.Text>
           North Indian, Chinese, Street Food
          </Card.Text>
          <Button variant="primary">Order Online</Button>
        </Card.Body>
      </Card></Link></Col>);
      })
        return (
            <Container>
              
            <Row>
              {restaurants}
            
            </Row>
              
          </Container>
        )
    }
}
