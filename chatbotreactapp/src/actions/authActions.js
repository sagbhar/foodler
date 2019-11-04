import { SET_CURRENT_USER, GET_ERRORS, GET_CATALOG } from "./types";
import Cookies from "universal-cookie";
import axios from "axios";
import jwt_decode from "jwt-decode";
import isEmpty from "../validation/is-empty";
import setHeaders from "../utils/setHeaders";
const cookies = new Cookies();


export const loginUser = userData => dispatch => {
  axios({
    method: "post",
    url: "http://localhost:8765/auth/token",
    data: userData,
    headers:{
        'Authorization': 'Basic aGVuZGktY2xpZW50OmhlbmRpLXNlY3JldA=='
    }
  })
    .then(res => {
      return res.data;
    })
    .then(data => {
      if (!isEmpty(data.access_token)) {
        cookies.set("AccessToken", data.access_token, {
          path: "/",
          maxAge: 50000
        });
        handleLogin(dispatch);
       
      }
    })
    .catch(error =>
      dispatch({
        type: GET_ERRORS,
        payload: error.response.data
      })
    );
};
const handleLogin = (dispatch)  => {
  axios({
      method: 'post',
      url: "http://localhost:8765/login/login",
      headers: {
          'Authorization': 'Bearer '+cookies.get('AccessToken')
      }
    })
    .then(res => {
      return res.data;
    })
    .then(data  => {
      console.log("loginData" + data);
      const token = cookies.get("AccessToken");
      const decoded = jwt_decode(token);
      console.log(decoded);
      dispatch(setCurrentUser(decoded));
    })
    .catch(error => {
      console.error("ErrorData" + error);
    });
};

// sets the current user

export const setCurrentUser = (decoded) => {
  return {
    type: SET_CURRENT_USER,
    payload: decoded
  }
}

// Logs user out 

export const logoutUser = () => dispatch => {
  cookies.remove('AccessToken');
  dispatch(setCurrentUser({}));
  dispatch({
    type: GET_CATALOG, 
    payload: []
  })

}
