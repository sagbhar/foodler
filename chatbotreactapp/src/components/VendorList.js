import React, { Component } from 'react';
import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import Header  from './Header';
import '../vendorList.css';
import Cookies from 'universal-cookie';
import { getCatalog } from '../actions/catalogActions';
import img from '../img/loginBackground.jpg';


class VendorList extends Component {
  constructor(props) {
    super(props);
    this.props.getCatalog();
  }
    render() {
      const { catalog } = this.props;
      let shops = [];
      catalog.map((shop) => {
        let a = "/restaurant/"+shop.shopId;
        shops.push(<Col ><Link to={a} ><Card style={{ width: '18rem' }}>
        <Card.Img variant="top" src={img} />  
        <Card.Body>
          <Card.Title >{shop.restaurantName}</Card.Title>
          <Card.Text>
           North Indian, Chinese, Street Food
          </Card.Text>
          <Button variant="primary">Order Online</Button>
        </Card.Body>
      </Card></Link></Col>);
      })
        return (
          
            <Container>
              <Header />
            <Row>
              {shops}
            
            </Row>
              
          </Container>
        )
    }
}

VendorList.propTypes = {
  getCatalog : PropTypes.func.isRequired
}

const mapStateToProps = state => ({
  catalog: state.catalog.shops
});

export default connect(mapStateToProps, { getCatalog })(VendorList);
