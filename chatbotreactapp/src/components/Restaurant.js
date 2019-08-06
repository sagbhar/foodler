import React, { Component } from "react";
import RESTAURANTS from "../restaurants";
import { connect } from 'react-redux';
import axios from 'axios';
import { Container, Row, Col, Card, Button, Modal } from "react-bootstrap";
import Header from './Header';
import { Link } from "react-router-dom";
import "../restaurant.css";
import FoodItem from "./FoodItem";
import Cookies from "universal-cookie";
import OrdersService from "./OrdersService";
import render from 'react-dom';
import dosa from '../img/Dosa-512.png';
import upma from '../img/upma.jpeg';
import appam from '../img/appam-01.jpg';


export default class Restaurant extends Component {
  constructor(props) {
    super(props);
    this.generateCatalog = this.generateCatalog.bind(this);
    this.state = {
      restaurantDetails: [],
      shopId: "",
      orderId: null,
      userId: null,
      totalAmt: 0,
      status: "P",
      orderItems: [],
      modalShow: false,
      setModalShow: this.setModalShow.bind(this)
    };
    this.updateSelected = this.updateSelected.bind(this);
    this.createOrder = this.createOrder.bind(this);
   
    //this.setModalShow = this.setModalShow.bind(this);
  }
  setModalShow(value) {

    this.state.modalShow = value;
  }
  createOrder(orderData) {
    OrdersService.createOrderNew(orderData)
      .then(response => {
        this.props.history.push(`/orderReview/${response.data.orderId}`);
      })
      .catch(error => console.log(error));
  }
  componentWillMount() {
    this.generateCatalog();
  }
  updateSelected(foodItem, quantity) {
    let orderItems = this.state.orderItems,
      flagForUpdate = false;
    this.state.orderItems.map(orderItem => {
      if (orderItem.foodItemId === foodItem.foodItemId) {
        orderItem.inventory = quantity;
        flagForUpdate = true;
      }
    });
    if (!flagForUpdate) {
      this.state.orderItems.push({
        foodItemId: foodItem.foodItemId,
        orderItemsPrice: foodItem.price,
        inventory: quantity,
        status: "P"
      });
    }
    this.updateTotal();
    console.log(this.state.orderItems);
  }
  updateTotal() {
    let total = 0;
    this.state.orderItems.map(orderItem => {
      total += orderItem.orderItemsPrice * orderItem.inventory;
    });
    this.setState({ totalAmt: total });
  }

  generateCatalog() {
    const cookies = new Cookies();
    let URL =
      "http://localhost:8765/catalog/getRestaurantDetails/" +
      this.props.match.params.id;
    fetch(URL, {
      method: "GET",
      headers: {
        'Authorization' : 'Bearer '+cookies.get('AccessToken')
      }
    })
      .then(res => {
        return res.json();
      })
      .then(data => {
        if (data.error === "invalid_token") {
          this.props.history.push("/login");
        } else {
          this.setState({ restaurantDetails: data });
        }
      })
      .catch(error => {
        console.error("ErrorData" + error);
      });
  }
  render() {
   // const [ modalShow, setModalShow ] = React.useState(false);
    let foodItems = [];
    let restaurantDetails = this.state.restaurantDetails;
    const foodItemUrls = ['https://cdn0.iconfinder.com/data/icons/south-indian-meals/150/Dosa-512.png',
    'https://cdn.dribbble.com/users/2014642/screenshots/4815948/appam-01.jpg', 'https://i.ytimg.com/vi/O4g_eSdwktM/maxresdefault.jpg'];

    let foodItemsList = restaurantDetails.restaurantFoodItems;
    if (undefined !== foodItemsList) {
      foodItemsList.map((foodItem, index) => {
        foodItem.imageUrl = foodItemUrls[index];
        foodItems.push(
          <FoodItem foodItem={foodItem} updateSelected={this.updateSelected} />
        );
      });
    }
    return (
      <Container>
        <Header />
        <Row>
          <Col>
            <a onClick={() => {this.props.history.push('/catalog')}} className="btn btn-sm btn-info">
              {" "}
              Go Back
            </a>
          </Col>
        </Row>
        <Row>
          <Col>
            <h1 className="restaurantName">{restaurantDetails.restaurantName}</h1>
          </Col>
        </Row>
        <Row>
          <Col>{foodItems}</Col>
        </Row>
        <Row>
          <Col className="reivewOrder">
            {" "}
            <button
              className="btn btn-success"
              onClick={() =>
                this.createOrder({
                  orderId: this.state.orderId,
                  userId: this.state.userId,
                  totalAmt: this.state.totalAmt,
                  status: this.state.status,
                  orderItems: this.state.orderItems
                })
              }
            >
              Review Your Order
            </button>
          </Col>
          <Col className="orderTotal"> Order Total : {this.state.totalAmt}</Col>
        </Row>
       
      </Container>
    );
  }
}
