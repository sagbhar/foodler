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
            <Card className="foodItemCard">
            <Card.Body className="foodItemCardBody">
                <div className="foodItem">
                 <div className="foodItemDetails">
                     <div className="foodItemImage">
                     <img src={this.props.foodItem.imageUrl} width={48} height={48}></img>
                     </div>
                     <div className="foodItemName">
                     <span>{this.props.foodItem.foodItem}</span>
                     </div>
                     <div className="foodItemPrice">
                     <span>{this.props.foodItem.price}</span>
                     </div>
                </div>
                <div className="quantity">
                    <div className="quantityWidget">
                    <span className="reduceQuantity" onClick={(evt) => { this.reduceQuantity();this.props.updateSelected(this.props.foodItem, this.state.quantity);  }}>-</span>
                    <span className="count">{this.state.quantity}</span>
                    <span className="increaseQuantity" onClick={(evt) => {this.increaseQuantity();this.props.updateSelected(this.props.foodItem, this.state.quantity); }}>+</span>
                    </div>
                 </div>
                 
                 <div>
                 </div>
                </div>
                </Card.Body>
            </Card>
        )
    }
}
