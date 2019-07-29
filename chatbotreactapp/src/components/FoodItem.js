import React, { Component } from 'react';
import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import '../foodItem.css';
import OrdersService from './OrdersService';

export default class FoodItem extends Component {
    constructor(props) {
        super(props);
        this.reduceQuantity = this.reduceQuantity.bind(this);
        this.increaseQuantity = this.increaseQuantity.bind(this);
        this.createOrder = this.createOrder.bind(this);
        this.state= {
            quantity:0,
            orderItemDetails:{
                orderId : null,
                foodItemId : this.props.foodItem.foodItemId,
                orderItemsPrice :this.props.foodItem.price,
                inventory :'0',
                status : 'P'
            }
        }
    }

    createOrder(orderItemDetails){
        alert('orderId' + this.state.orderItemDetails.orderId);
        OrdersService.createOrder(orderItemDetails)
        .then(response => {
            console.log(response.data.orderId);
            this.setState(
                {
                    orderItemDetails:{
                        orderId : response.data.orderId
                    }

                }
            )
            console.log("saetting orderId" + this.state.orderItemDetails.orderId);
        })
        .catch(error => console.log(error))
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
                    <span className="reduceQuantity" onClick={this.reduceQuantity && this.props.updateSelected(this.props.foodItem)}>-</span>
                    <span className="count">{this.state.quantity}</span>
                    <span className="increaseQuantity" onClick={this.increaseQuantity}>+</span>
                 </div>
                 
                 <div>
                     <button className="btn btn-success" onClick={() =>this.createOrder(
                {orderId : this.state.orderItemDetails.orderId,
                foodItemId : this.props.foodItem.foodItemId,
                orderItemsPrice :this.props.foodItem.price,
                inventory : this.state.quantity,
                status : 'P'})}>Add</button>
                 </div>
                </div>
                </Card.Body>
            </Card>
        )
    }
}
