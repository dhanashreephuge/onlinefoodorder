import React, { useEffect } from 'react';
import { ThemeProvider } from '@mui/material/styles';
import { darkTheme } from './Theme/Theme'; 
import Navbar from './component/Navbar/Navbar'; 
import CssBaseline from '@mui/material/CssBaseline';
import Home from './component/Home/Home';
import RestaurantDetails from './component/Restaurant/RestaurantDetails';
import Cart from './component/Cart/Cart';
import Profile from './component/Profile/Profile';
import CustomerRoute from './Routers/CustomerRoute';
import { useDispatch, useSelector } from 'react-redux';
import { getUser } from './component/State/Authentication/Action';
import { store } from './component/State/store';
import { findCart } from './component/State/Cart/Action';

function App() {
  const dispatch=useDispatch()
  const jwt=localStorage.getItem("jwt");
  const { auth } = useSelector((store) =>store);

  useEffect(() =>{
    dispatch(getUser(auth.jwt || jwt));
    dispatch(findCart(jwt));
  }, [auth.jwt]);

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <Navbar />
      {/* <Home /> */}
      {/* <RestaurantDetails /> */}
      {/* <Cart /> */}
      {/* <Profile /> */}
      <CustomerRoute />
    </ThemeProvider>
  );
}

export default App;
