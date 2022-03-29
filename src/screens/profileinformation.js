import React from 'react';
import {Text,View,Image, TextInput} from 'react-native';
import Icon from '@expo/vector-icons/AntDesign';

export default class profileinformation extends React.Component{

    render(){
        const {navigate} = this.props.navigation
        return(
            //Adding application logo
            <View style={{backgroundColor:"#FFF",height:"100%"}}>
                <Image source ={require('../images/logo.png')}
                    style={{width:"100%",height:"20%",marginTop:100}}
                />
                {/* adding slogan of application */}
                <Text
                 style={{
                     fontSize:30,
                     alignSelf:"center",
                 }}
                >Stay Healthy</Text>

                {/* adding information related that how to use that screen */}
                <Text
                style={{
                    marginHorizontal:55,
                    textAlign:'center',
                    marginTop:5,
                    opacity:0.4
                }}
                >
                    Plase Enter your Email and Telephone Number
                </Text>

                {/* adding  and designing email and phone number textboxes */}
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
                    <Icon name="mail" color="#32afb6" size={26}/>
                    <TextInput 
                        placeholder="Email"
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
                    <Icon name="phone" color="#32afb6" size={26}/>
                    <TextInput 
                        placeholder="Telephone Number"
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
                    }}>Update</Text>
                </View>
            
            </View>
        );
    }
}