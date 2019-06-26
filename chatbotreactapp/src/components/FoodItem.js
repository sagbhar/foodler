import React, { Component } from 'react';
import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import '../foodItem.css';

export default class FoodItem extends Component {
    constructor(props) {
        super(props);
        this.reduceQuantity = this.reduceQuantity.bind(this);
        this.increaseQuantity = this.increaseQuantity.bind(this);
        this.state= {
            quantity:0
        }
    }

    reduceQuantity() {
        this.setState({quantity: this.state.quantity ? --this.state.quantity: this.state.quantity})
   }
    increaseQuantity() {
        this.setState({quantity: ++this.state.quantity})
   }
    render() {
        return (
            <Card>
            <Card.Body>
                <div className="foodItem">
                 <div className="foodItemDetails">
                     <span>{this.props.foodItem.foodItem}</span>
                     <span>{this.props.foodItem.price}</span>
                </div>
                <div className="quantity">
                    <span className="reduceQuantity" onClick={this.reduceQuantity}>-</span>
                    <span className="count">{this.state.quantity}</span>
                    <span className="increaseQuantity" onClick={this.increaseQuantity}>+</span>
                 </div>
                </div>
                </Card.Body>
            </Card>
        )
    }
}
