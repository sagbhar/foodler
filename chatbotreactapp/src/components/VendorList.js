import React, { Component } from 'react';
import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import { Link } from 'react-router-dom';
import RESTAURANTS from '../restaurants';
import '../vendorList.css';
export default class VendorList extends Component {
    render() {
      let restaurants = [];
      RESTAURANTS.map((restaurant) => {
        let a = "/VendorCatalog/"+restaurant._id;
        restaurants.push(<Col ><Link to={a} ><Card style={{ width: '18rem' }}>
        <Card.Img variant="top" src={restaurant.imageUrl} />
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
