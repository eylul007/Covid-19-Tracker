import React from 'react';
import {Text,View,Image, Button,TextInput} from 'react-native';
import Icon from '@expo/vector-icons/AntDesign';
//import { useFonts, Inter_900Black, Inter_700Bold } from '@expo-google-fonts/inter';

export default class Login extends React.Component{

    render(){
        const {navigate} = this.props.navigation
        return(
            <View style={{backgroundColor:"#FFF",height:"100%"}}>
                {/* adding application logo to login screen */}
                <Image source ={require('../images/logo.png')}
                    style={{width:"100%",height:"20%",marginTop:100}}
                />
                {/* Application slogan */}
                <Text
                 style={{
                     fontSize:30,
                     alignSelf:"center",
                 }}
                >Stay Healthy</Text>

                {/* Information text that how to use the screen */}

                <Text
                style={{
                    marginHorizontal:55,
                    textAlign:'center',
                    marginTop:5,
                    opacity:0.4
                }}
                >
                    
                Please Login with E-devlet. (If you are a foreign national please proceed with the other option.)     
                </Text>

                <View 
                
                //Design E-devlet login button

                style={{
                    marginHorizontal:55,
                    alignItems:"center",
                    justifyContent:"center",
                    marginTop:30,
                    backgroundColor:"red",
                    paddingVertical:10,
                    borderRadius:23,
                    flexDirection:'row'
                }
                
                }>
                 <Image source ={require('../images/e-devlet.png')}
                    style={{marginRight:10}}/>

                    {/* if the user click e devlet login button the application direct to fake e devlet interface */}
                    <Text 
                      onPress={()=>navigate('edevletlogin')}
                    style={{
                        color:"white",
                    }}>E-devlet Login.</Text>
                </View>

                {/* It is for login button for foreign users */}
                <Text 
                
                //If the user press that button , application redirect to inerlogin page
                onPress={()=>navigate('interlogin')}
                
                style={{
                    alignSelf:"center",
                    color:"#00716F",
                    paddingVertical:30
                }}>Foreign Login</Text>
            </View>
        );
    }
}