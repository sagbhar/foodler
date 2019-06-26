import React, { Component } from 'react';
import RESTAURANTS from '../restaurants';
import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import '../restaurant.css';
import FoodItem from './FoodItem';

export default class Restaurant extends Component {
    render() {
        let foodItems = [];
        let restaurant = RESTAURANTS.filter((restaurant, index) => 
             restaurant._id === this.props.match.params.id
        );

        restaurant[0].restaurantFoodItems.map((foodItem) => {
            foodItems.push( <FoodItem foodItem={foodItem}/> )
        })
        return (
           <Container>
               <Row>
                   <Col>
                   <h1>{restaurant[0].restaurantName}</h1>
                   </Col>
                   </Row>
               <Row>
                <Col>
             {foodItems}
           </Col>
             </Row>
            </Container>
               
        )
    }
}
