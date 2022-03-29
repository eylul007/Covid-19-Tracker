import React, {Component} from 'react';
import {View, Text,StyleSheet,Image,Button,ImageBackground} from 'react-native';
import Icon from '@expo/vector-icons/Ionicons';
import Icons from '@expo/vector-icons/AntDesign';
import { ScrollView } from 'react-native-gesture-handler' 
import Status from '../components/Status';
import Cards from '../components/Cards';
import Foother from '../components/Foother';


export default class Home extends Component {


    render(){
        const {navigate} = this.props.navigation
        return(
            <View style={styles.container}>
                <ImageBackground
                    // background picture insert of the top
                     source={require("../images/sss.jpg")}
                    style={styles.map}
                >
                    <View style={styles.col}>
                        <View style={styles.settingContainer}>
                            {/* Insert Setting icon top-right of the screen */}
                             <Icons 
                             onPress={()=>navigate('settings')}
                             name="setting" color="#000" size={26} />
                        </View>
                    </View>
                    {/* Insert our application logo on the screen */}
                    <Image source={require('../images/logom.png')} style={{width:'60%',height:'40%',marginTop:10,marginLeft:85}}/>

                    <View style={styles.colContainer}>
                        <Text style={styles.headerText}>Update Status</Text>
                        <View style={styles.reloadContainer}>
                            <Icon name="md-refresh" size={24} color="red"/>
                        </View>
                    </View>
                </ImageBackground>
                {/* Showing current status with Status.js component */}
                <Status
                    name="Your current status is safe"
                    
                /> 
                <ScrollView 
                    //To allign the cards side to side
                    style={{marginTop:30}}
                    showsHorizontalScrollIndicator={false}
                    horizontal
                >
                    {/* Entering card details it will retrive the data from db */}
                    <Cards
                        onPress={()=>this.props.navigation.navigate('Detail')}
                        icon="md-pulse"
                        title="TOTAL CASES"
                        bg="red"
                        number="111 111"
                    />
                     <Cards
                        icon="ios-git-network"
                        title="RECOVERED"
                        bg="#32afb0"
                        number="222 222"
                    />
                     <Cards
                        icon="ios-heart-dislike"
                        title="DEATH CASES"
                        bg="#000"
                        number="333 333"
                      
                    />
                </ScrollView>
                <View style={{marginBottom:70}}>
                    <Foother
                        name="Number of Contact People"
                        number="10"
                    />

                </View>
            </View>
        )
    }
}
const styles = StyleSheet.create({
    container:{
        flex:1,
        backgroundColor:"#dcf0ee"

    },
    //screens operations zoom etc.
    map:{
        height:200,
        paddingTop:25,
        paddingHorizontal:20,
        backgroundColor:'#FFF',
        marginBottom:15
    
    },
    col:{
        flexDirection:'row'
    },
    settingContainer:{
        marginTop:20,
        width:"50%",
        marginLeft:325

    },
    colContainer:{
        flexDirection:"row",
        paddingHorizontal:30,
        marginTop:10,
        alignItems:'center',
    },
    headerText:{
        fontWeight:"bold",
        fontSize:20,
        marginLeft:65,
        color:"black"
     
    },
    reloadContainer:{
        backgroundColor:"#FFF",
        elevation:2,
        width:40,
        height:40,
        borderRadius:20,
        alignItems:'center',
        justifyContent:"center",
        marginLeft:50
    }
});