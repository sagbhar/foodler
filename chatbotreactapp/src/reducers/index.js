import AuthReducer from './authReducer';
import ErrorReducer from './errorReducer';
import { combineReducers } from 'redux';
import CatalogReducer from './catalogReducer';

export default combineReducers({
    auth: AuthReducer,
    errors: ErrorReducer,
    catalog: CatalogReducer
});