
import React from 'react';
import {createStackNavigator} 
    from '@react-navigation/stack';
import Login from '../screens/Login';
import Register from '../screens/Register'
import interlogin from '../screens/interlogin'
import edevletlogin from '../screens/edevletlogin'
import settings from '../screens/settings'
import profileinformation from '../screens/profileinformation';
import Home from '../screens/Home';
import Onboarding from '../screens/Onboarding'

const Stack = createStackNavigator();
const screenOptionStyle = {
    headerShown: false
}

const HomeStackNavigator = () => {
    return(
        // we insert the home page 
        <Stack.Navigator screenOptions={screenOptionStyle}>
             <Stack.Screen
                name = "Onboarding"
                component={Onboarding}
            /> 
            <Stack.Screen
                name = "Login"
                component={Login}
            />
            <Stack.Screen
                name = "settings"
                component={settings}
            />
             <Stack.Screen
                name = "Register"
                component={Register}
            />
            <Stack.Screen
                name = "interlogin"
                component={interlogin}
            />
            <Stack.Screen
                name = "edevletlogin"
                component={edevletlogin}
            />
            <Stack.Screen
                name = "profileinformation"
                component={profileinformation}
            />
            <Stack.Screen
                name = "Home"
                component={Home}
            />


        </Stack.Navigator>
    )
}

export default HomeStackNavigator;