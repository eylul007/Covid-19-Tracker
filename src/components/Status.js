import React from 'react';
import Icons from '@expo/vector-icons/AntDesign';
import {
    View,
    Text,
    StyleSheet,
    Image
} from 'react-native'

const Status = (props) => {
    return(
        <View style={styles.container}>
            <View >
                {/* Put tik icon and make green */}
                <Icons name="checkcircle" size={50} color= 'green'/>
            </View>
            {/* Getting text status from home.js and put insede the status */}
            <Text style={styles.statusName}>{props.name}</Text>
        </View>
    );
}
// Allign test and change required field
const styles = StyleSheet.create({
    container:{
        borderRadius:30,
        borderColor:"#6a706e",
        borderWidth:1,
        marginHorizontal:30,
        paddingHorizontal:10,
        paddingVertical:50,
        flexDirection:"row",
        alignItems:"center",
        marginTop:5,
        backgroundColor:'#dcf0ee',
        marginBottom:0
    },

    statusName:{
        fontWeight:'bold',
        color:"#000",
        fontSize:20,
        marginLeft:20
    },
})
export default Status;