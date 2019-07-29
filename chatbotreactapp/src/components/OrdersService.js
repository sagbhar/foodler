import React  from 'react';
import axios from 'axios';
import Cookies from 'universal-cookie';

class OrdersService {

    createOrder(orderItemDetails){
        const cookies = new Cookies(); 
        console.log("dfdfdfdfd");
        let contentType = 'Content-Type';
        console.log('orderItemDetails' +orderItemDetails);
        let authToken = cookies.get('AccessToken');
        return axios.post("http://localhost:8765/orders/add", orderItemDetails,
        {
            headers : {
                Accept : 'application/json',
                Authorization : 'Bearer '+ authToken,
                ContentType : 'application/x-www-form-urlencoded'
            }
        }
        );
    }
    findOrders(){
        const cookies = new Cookies(); 
        let contentType = 'Content-Type';
        let authToken = cookies.get('AccessToken');
        return axios.get("http://localhost:8765/orders/findAll", 
        {
            headers : {
                Accept : 'application/json',
                Authorization : 'Bearer '+ authToken,
                ContentType : 'application/x-www-form-urlencoded'
            }
        }
        );
    }
}
export default new OrdersService;