import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import MakeAudioBook from './administor/container/MakeAudioBook';
import Navbar from './common/container/Navbar';
import Main from './main/container/Main'
import FindBook from './book/container/FindBook'
import User from './user/container/User'
import Login from './user/container/Login'
import BookDetail from './book/container/BookDetail';
import SignIn from './user/container/SignIn';

import reportWebVitals from './reportWebVitals';
import { Route, BrowserRouter } from 'react-router-dom'

import { Provider } from "react-redux";
import { createStore } from "redux";
import rootReducer from "./store/reducers"

const store = createStore(rootReducer);

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
    {/* <App /> */}
    <BrowserRouter>
      <Navbar />
      <div className="router-body">
        <Route exact path='/' component={Main}/>
        <Route path='/makeaudio' component={MakeAudioBook}/>
        <Route path='/find' component={FindBook}/>
        <Route path='/login' component={Login}/>
        <Route path='/book' component={BookDetail}/>
        <Route path='/signin' component={SignIn}/>
      </div>
    </BrowserRouter>
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
