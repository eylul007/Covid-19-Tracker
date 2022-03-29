import React from 'react';
import {Text,View,Image, TextInput} from 'react-native';
import Icon from '@expo/vector-icons/AntDesign';
import Icons from '@expo/vector-icons/Ionicons';

export default class settings extends React.Component{

    render(){
        const {navigate} = this.props.navigation
        return(
            <View style={{backgroundColor:"#FFF",height:"100%"}}>

                {/* adding app logo */}
                <Image source ={require('../images/logo.png')}
                    style={{width:"100%",height:"20%",marginTop:100}}
                />
                <Text
                 style={{
                     fontSize:30,
                     marginBottom: 15,
                     alignSelf:"center",
                 }}
                >Settings</Text>

   

                {/* adding and designing personal information, bluetooth settings and log out buttons */}
                <View 
                
                style={{
                    marginHorizontal:55,
                    alignItems:"center",
                    justifyContent:"center",
                    marginTop:30,
                    backgroundColor:"#32afb6",
                    paddingVertical:10,
                    borderRadius:23,
                    flexDirection:'row'
                }
                
                }>
                    <Icon name="user" color="white" size={26}/>

                    <Text 
                      onPress={()=>navigate('profileinformation')}
                    style={{
                        color:"white",
                      //  fontFamily:"Inter_700Bold"
                    }}>Personal Information</Text>
                </View>

                <View 
                
                style={{
                    marginHorizontal:55,
                    alignItems:"center",
                    justifyContent:"center",
                    marginTop:30,
                    backgroundColor:"#32afb6",
                    paddingVertical:10,
                    borderRadius:23,
                    flexDirection:'row'
                }
                
                }>
                    <Icons name="bluetooth" color="white" size={26}/>

                    <Text 
                      onPress={()=>navigate('settings')}
                    style={{
                        color:"white",
                    }}>Bluetooth Settings</Text>
                </View>

                <View 
                
                style={{
                    marginHorizontal:55,
                    alignItems:"center",
                    justifyContent:"center",
                    marginTop:30,
                    backgroundColor:"#32afb6",
                    paddingVertical:10,
                    borderRadius:23,
                    flexDirection:'row'
                }
                
                }>
                <Icons name="log-out-outline" color="white" size={26} />

                    <Text 
                      onPress={()=>navigate('settings')}
                    style={{
                        color:"white",
                    }}> Log Out </Text>
                </View>


            </View>
        );
    }
}