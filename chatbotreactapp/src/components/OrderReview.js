import React, {Component} from 'react';
import OrdersService from './OrdersService';
import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import '../foodItem.css';

class OrderReview extends Component {
  constructor(props) {
    super(props);
    this.getOrderDetails = this.getOrderDetails.bind(this);
    this.state ={
      orderId : this.props.match.params.orderId,
      orderItems :[]
    }
  }
  getOrderDetails(orderId){
    OrdersService.getOrderDetails(orderId)
    .then(response => {
      console.log(response)
     this.setState({
       orderItems : response.data
     })
      console.log(this.state.orderItems)
    }
   )
  }
  componentDidMount() {
    this.getOrderDetails(this.state.orderId);
  }
  render(){
    let orderItemContent = [];
      this.state.orderItems.map(
        itemData =>{
          orderItemContent.push(
          <div className="foodItem">
            <div className="foodItemDetails">
              <span>{itemData.foodItemId}</span>
              <span>{itemData.orderItemsPrice}</span>
            </div>
            <div className="quantity">
              <span className="reduceQuantity">-</span>
              <span className="count">{itemData.inventory}</span>
              <span className="increaseQuantity">+</span>
            </div>

            <div>
            </div>
          </div>
          )}
      )
   
    return(
      <div className="container">
        <Card>
          <Card.Body>
           {orderItemContent}
          </Card.Body>
        </Card>
      </div>
    );
}
}

export default OrderReview;