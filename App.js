import React from 'react';
import {NavigationContainer} 
  from '@react-navigation/native'
import HomeStackNavigator 
  from './src/navigations/Navigator';
  


//Direct to login page
const App = () => {
  
  return(
    <NavigationContainer>
      <HomeStackNavigator/>
    </NavigationContainer>
  )
}
export default App;
