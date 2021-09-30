import { combineReducers } from "redux";
import bookReducer from "./bookReducer";
import userReducer from "./userReducer";
import commentReducer from "./commentReducer";
import { persistReducer } from "redux-persist";
import storage from 'redux-persist/lib/storage';

const persistConfig = {
    key: 'root',
    storage
}

const rootReducer = combineReducers({bookReducer, userReducer, commentReducer});
const persistedReducer = persistReducer(persistConfig, rootReducer);
export default persistedReducer;