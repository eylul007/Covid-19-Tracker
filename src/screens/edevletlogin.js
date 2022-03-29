import React from 'react';
import {Text,View,Image, TextInput} from 'react-native';
import Icon from '@expo/vector-icons/AntDesign';

//Fake Edevlet interface
export default class edevletlogin extends React.Component{

    render(){
        const {navigate} = this.props.navigation
        return(
            <View style={{backgroundColor:"#FFF",height:"100%"}}>
                {/* insert edevlet icon */}
                <Image source ={require('../images/edevlets.png')}
                    style={{width:"100%",height:"20%",marginTop:100}}
                />
                <Text
                 style={{
                     fontSize:30,
                     alignSelf:"center",
                 }}
                >e-Devlet Kapısı Kimlik Doğrulama Sistemi</Text>

                <Text
                style={{
                    marginHorizontal:55,
                    textAlign:'center',
                    marginTop:5,
                    opacity:0.4
                }}
                >
                   
                </Text>
                
                {/* adding and designing TC number password textboxes and login button */}
                <View style={{
                    flexDirection:"row",
                    alignItems:"center",
                    marginHorizontal:55,
                    borderWidth:2,
                    marginTop:50,
                    paddingHorizontal:10,
                    borderColor:"#000",
                    borderRadius:23,
                    paddingVertical:2
                }}>
                    <TextInput
                        placeholder="T.C. Number" 
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
                    borderColor:"#000",
                    borderRadius:23,
                    paddingVertical:2
                }}>
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
                    backgroundColor:"#0968ba",
                    paddingVertical:10,
                    borderRadius:23
                }}>
                    <Text 
                    onPress={()=>navigate('Home')}
                    style={{
                        color:"white",
                    }}
                    
                    >Login</Text>
                </View>

            </View>
        );
    }
}