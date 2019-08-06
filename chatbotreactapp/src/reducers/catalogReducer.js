import { GET_CATALOG } from '../actions/types';

const initialState = {shops:[]};
export default function(state = initialState, action) {
    console.log(action.payload);
    switch(action.type) {
        case GET_CATALOG:
            return {
                ...state,
                shops: action.payload
            }
        default: 
            return state;
    }
}