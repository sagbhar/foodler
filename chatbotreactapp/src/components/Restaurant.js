import React, { Component } from "react";
import RESTAURANTS from "../restaurants";
import { Container, Row, Col, Card, Button } from "react-bootstrap";
import { Link } from 'react-router-dom';
import "../restaurant.css";
import FoodItem from "./FoodItem";
import Cookies from "universal-cookie";
import OrdersService from "./OrdersService";

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
      orderItems: []
    };
    this.updateSelected = this.updateSelected.bind(this);
    this.createOrder = this.createOrder.bind(this);
  }

  createOrder(orderData) {
    OrdersService.createOrderNew(orderData)
      .then(response => {
        console.log(response);
        OrdersService.findOrders(response.data.orderId).then(ordersData =>
          console.log(ordersData)
        );
        this.props.history.push(`/ordersReview/${response.data.orderId}`);
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
    let headers = new Headers();
    const cookies = new Cookies();
    headers.append("Accept", "application/json");
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    headers.append("Authorization", "Bearer " + cookies.get("AccessToken"));
    let URL =
      "http://localhost:8765/catalog/getRestaurantDetails/" +
      this.props.match.params.id;
    fetch(URL, {
      method: "GET",
      headers: headers
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
    let foodItems = [];
    let restaurantDetails = this.state.restaurantDetails;

    let foodItemsList = restaurantDetails.restaurantFoodItems;
    if (undefined !== foodItemsList) {
      foodItemsList.map(foodItem => {
        foodItems.push(
          <FoodItem foodItem={foodItem} updateSelected={this.updateSelected} />
        );
      });
    }
    return (
      <Container>
        <Row>
          <Col>
            <Link to="/catalog" className="btn btn-sm btn-info">
              {" "}
              Go Back
            </Link>
          </Col>
        </Row>
        <Row>
          <Col>
            <h1>{restaurantDetails.restaurantName}</h1>
          </Col>
        </Row>
        <Row>
          <Col>{foodItems}</Col>
        </Row>
        <Row>
          <Col>
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
          <Col> Order Total : {this.state.totalAmt}</Col>
        </Row>
      </Container>
    );
  }
}
