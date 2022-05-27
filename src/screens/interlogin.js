import React from 'react';
import {Text,View,Image, TextInput} from 'react-native';
import Icon from '@expo/vector-icons/AntDesign';

export default class interlogin extends React.Component{

    render(){
        const {navigate} = this.props.navigation
        return(
            <View style={{backgroundColor:"#FFF",height:"100%"}}>
                {/* adding the app logo */}
                <Image source ={require('../images/logo.png')}
                    style={{width:"100%",height:"20%",marginTop:100}}
                />
                <Text
                 style={{
                     fontSize:30,
                     alignSelf:"center",
                 }}
                //  adding app slogan
                >Stay Healthy</Text>

                 {/* adding text about how to use the screen */}
                <Text
                style={{
                    marginHorizontal:55,
                    textAlign:'center',
                    marginTop:5,
                    opacity:0.4
                }}
                >
                    Plase Login Your Information.
                </Text>

                {/* adding and designing password number textboxes, password textboxes, login button and new user buttons  */}
                <View style={{
                    flexDirection:"row",
                    alignItems:"center",
                    marginHorizontal:55,
                    borderWidth:2,
                    marginTop:50,
                    paddingHorizontal:10,
                    borderColor:"#32afb6",
                    borderRadius:23,
                    paddingVertical:2
                }}>
                    <Icon name="user" color="#32afb6" size={26}/>
                    <TextInput 
                        placeholder="Passport number"
                        style={{paddingHorizontal:10}}
                    />

                    

                </View>
                <View style={{
                    flexDirection:"row",
                    alignItems:"center",
                    marginHorizontal:55,
                    borderWidth:2,
                    marginTop:15,
                    paddingHorizontal:10,
                    borderColor:"#32afb6",
                    borderRadius:23,
                    paddingVertical:2
                }}>
                    <Icon name="lock" color="#32afb6" size={26}/>
                    <TextInput 
                        placeholder="Password"
                        style={{paddingHorizontal:10}}
                    />

                    

                </View>

                <View style={{
                    marginHorizontal:55,
                    alignItems:"center",
                    justifyContent:"center",
                    marginTop:30,
                    backgroundColor:"#32afb6",
                    paddingVertical:10,
                    borderRadius:23
                }}>
                    <Text style={{
                        color:"white",
                    }}>Login</Text>
                </View>
                <Text 
                
                onPress={()=>navigate('Register')}
                
                style={{
                    alignSelf:"center",
                    color:"#32afb6",
                    paddingVertical:30
                }}>New User</Text>
            </View>
        );
    }
}