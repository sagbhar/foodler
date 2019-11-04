import { GET_CATALOG, GET_ERRORS } from './types';
import Cookies from 'universal-cookie';
import store from '../store';
import axios from 'axios';
// Gets all vendors
const cookies = new Cookies();
export const getCatalog = () => dispatch => {
        if(!store.getState().catalog.shops.length) {
        axios({
          method: 'GET',
          url: 'http://localhost:8765/catalog/getAllRestaurants',
          headers: {
              'Authorization' : 'Bearer '+ cookies.get('AccessToken')
          },
      })
          .then(res => {
              return res.data;
          })
          .then((data) => {
             dispatch({
                 type: GET_CATALOG,
                 payload: data
             });      
          })
          .catch((error) => {
             dispatch({
                 type: GET_ERRORS,
                 payload: error
             })
          });
        }
        else {
            return store.getState().catalog.shops;
        }
        
}