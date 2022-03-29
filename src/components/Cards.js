import React, {Component} from 'react';
import {View,StyleSheet,Text} from 'react-native'
import Icon2 from '@expo/vector-icons/MaterialCommunityIcons'
import Icon from '@expo/vector-icons/Ionicons'
import {TouchableOpacity} from 'react-native-gesture-handler'

export default class Cards extends Component{
    render(){
        return(
         <View style={{
            ...styles.container, 
            backgroundColor:this.props.bg
         }}>
            <View style={styles.col}>
               <Icon 
                 name={this.props.icon}
                 size={30}
                //  if background of the card is red icon will be white else red
                 color={this.props.bg == "red" ? "#FFF":"red"}
               />
            </View>
             <Text style={styles.title}>{this.props.title}</Text>
             {/* get the numberof  totalcases, recovered and death cases from home.js */}
            <Text style={{
                ...styles.number,//change style of the number of cases
                color:'#FFF'
            
            }}>
                {this.props.number}
            </Text>
         </View>
        )
    }
}
// Allign test and change required field
const styles = StyleSheet.create({
  container:{
    height:200,
    width:130,
    borderRadius:30,
    padding:15,
    marginLeft:20,
    marginRight:20
  },
  col: {
      flexDirection:"row"
  },
  title:{
      marginTop:60,
      color:"#FFF",
      fontWeight:"bold",
      fontSize:13
  },
  number:{
      fontWeight:'bold',
      fontSize:22
      
  }
})