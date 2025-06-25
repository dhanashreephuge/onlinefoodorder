import React from 'react';
import { ThemeProvider } from '@mui/material/styles';
import { darkTheme } from './Theme/Theme'; 
import Navbar from './component/Navbar/Navbar'; 
import CssBaseline from '@mui/material/CssBaseline';
import Home from './component/Home/Home';
import RestaurantDetails from './component/Restaurant/RestaurantDetails';
import Cart from './component/Cart/Cart';
import Profile from './component/Profile/Profile';

function App() {
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <Navbar />
      {/* <Home /> */}
      {/* <RestaurantDetails /> */}
      {/* <Cart /> */}
      <Profile />
    </ThemeProvider>
  );
}

export default App;
