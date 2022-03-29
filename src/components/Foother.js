import React from 'react';
import Icons from '@expo/vector-icons/AntDesign';
import {
    View,
    Text,
    StyleSheet,
} from 'react-native'

const Foother = (props) => {
    return(
        <View style={styles.container}>
            <View >
                {/* Insert information icon on foother */}
                <Icons
                    name="infocirlce"
                    size={20}
                    color="black"
                />
                
            </View>
            {/* Getting text and number of contactact people from Home.js */}
            <Text style={styles.footherName}>{props.name}</Text>
            <Text style={styles.number}>{props.number}</Text>
        </View>
    );
}
// Allign test and change required field
const styles = StyleSheet.create({
    container:{
        borderRadius:15,
        borderColor:"#6a706e",
        borderWidth:1,
        marginHorizontal:30,
        paddingHorizontal:20,
        paddingVertical:20,
        flexDirection:"row",
        alignItems:"center",
        marginTop:5,
        marginBottom:5
    },
    circle:{
        alignItems:'center',
        justifyContent:'center',
        width:20,
        height:20,
        borderRadius:10,
        backgroundColor:'#2b3240'
    },
    textInfo:{
        color:"#6a706e",
        fontWeight:"bold"
    },
    footherName:{
        fontWeight:'bold',
        color:"#000",
        fontSize:12,
        marginLeft:20
    },
    number:{
        fontWeight:'bold',
        color:'red',
        fontSize:14,
        marginLeft:90
    }
})
export default Foother;