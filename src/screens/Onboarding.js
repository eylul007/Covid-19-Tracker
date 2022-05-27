import React, { useEffect, useState , useRef } from 'react';
import { View, Text, StatusBar, SafeAreaView, TouchableOpacity, FlatList, ImageBackground} from 'react-native'
import {COLORS, SIZES} from '../constants/index'
import AntDesignIcons from 'react-native-vector-icons/AntDesign';
import data from '../data/onboarding'
import { useNavigation } from '@react-navigation/native';
import { color } from 'react-native/Libraries/Components/View/ReactNativeStyleAttributes';

const Onboarding= () =>{


    // Taking current page and set it
    const flatlistRef = useRef();

    const [currentPage,setCurrentPage]=useState(0);
    const [viewableItems,setViewableItems]=useState([]);

    const handleViewableItemsChanged= useRef(({viewableItems})=>{

        setViewableItems(viewableItems)


    })
    useEffect(() => {
        // If we are not in first page
        if(!viewableItems[0] || currentPage === viewableItems[0].index)
            return;

        // else we are in first page
        setCurrentPage(viewableItems[0].index)

    },[viewableItems])

    //Skip next page of onboarding
    const handleNext = () => {
        //If we are in last page of onboarding
        if(currentPage == data.length-1)
            return;
        
        // Scroll and position viewible areas, we are not in last page
        flatlistRef.current.scrollToIndex({
            animated: true,
            index: currentPage +1
        })

        
    }
  //Go back page of onboarding
    const handleBack = () => {
        //If we are in first page of onboarding, then there is nowhere to go back
        if(currentPage == 0)
        return;
        // Scroll and position viewible areas, go back to onboarding screens
        flatlistRef.current.scrollToIndex({
            animated: true,
            index: currentPage -1
        })
    }

    //When user want to go to last page, he/she clicks skip button
    const handleSkipToEnd = () => {
        // Scroll and position viewible areas and skip
        flatlistRef.current.scrollToIndex({
            animated: true,
            index: data.length -1
        })
    }



//Design of the header
    const renderTopSection = () => {

        return(
            <SafeAreaView>
                <View style={{
                    flexDirection:'row',
                    alignItems: 'center',
                    justifyContent: 'space-between',
                    paddingHorizontal: SIZES.base * 2
                }}>
                    {/*Back Button */}
                    <TouchableOpacity 
                    
                    onPress={handleBack}
                    style={{
                        padding: SIZES.base
                    }}>
                        {/* Back icon */}
                        {/* Hide back button on 1st screen */}
                        <AntDesignIcons name="left" style={{
                            fontSize: 25,
                            color: COLORS.black,
                            opacity:  currentPage ==0 ? 0:1
                        }}/>
                    </TouchableOpacity>

                    {/*Skip Button */}
                    {/* Hide skip button on 1st screen */}
                    <TouchableOpacity onPress={handleSkipToEnd}>
                        <Text style={{
                            fontSize:18,
                            color: COLORS.black,
                            opacity: currentPage == data.length-1 ? 0 : 1
                        }}>Skip</Text> 
                    </TouchableOpacity>

                </View>
            </SafeAreaView>

        )
    }
//Design of the dots and go to next page button
    const renderBottomSection =() =>{
        const navigation = useNavigation();

        return(
            <SafeAreaView>
                <View style={{
                    flexDirection: 'row',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    paddingHorizontal: SIZES.base * 2,
                    paddingVertical: SIZES.base *2 
                }}>
                    {/* pagination*/}
                    {/* Styling of dots */}
                    <View style={{flexDirection:'row', alignItems: 'center'}}>
                        {
                            [...Array(data.length)].map((_,index)=>(
                                <View
                                key={index} 
                                style={{
                                    width:10,
                                    height:10,
                                    borderRadius:5,
                                    backgroundColor: index==currentPage ? 
                                    COLORS.primary:COLORS.primary+'20' ,
                                    marginRight: 8
                                }}/>
                            ))
                        }


                    </View>
                    {/* NExt or Getstarde button */}
                    {/* if the current onboarding screen is not the last screen of onboarding then the skip button to next page appers
                        else get started button appers
                    */}
                    {
                        currentPage !=data.length -1 ?(
                            <TouchableOpacity
                            onPress={handleNext}
                             style={{
                                flexDirection: 'row',
                                alignItems: 'center',
                                justifyContent: 'center',
                                width: 60,
                                height:60,
                                borderRadius: 30,
                                backgroundColor: COLORS.primary
                            }}
                            activeOpacity={0.8}
                            >
                                <AntDesignIcons name="right"
                                style={{fontSize:18 , color: COLORS.white, opacity:0.3}}/>
                                <AntDesignIcons
                                name="right"
                                style={{fontSize:25 ,color: COLORS.white,marginLeft: -10}}
                                />
                            </TouchableOpacity>

                        ) : (
                            //Get Started Button
                            <TouchableOpacity style={{
                                paddingHorizontal: SIZES.base*2,
                                height: 60,
                                borderRadius: 30,
                                backgroundColor: COLORS.primary,
                                flexDirection:'row',
                                justifyContent: 'center',
                                alignItems: 'center'
                            }}>
                                <Text 
                               
                                onPress={() => {
                                    navigation.navigate('Login');
                                }}
                             
                                style={{
                                    color: COLORS.white,
                                    fontSize: 18,
                                    marginLeft: SIZES.base
                                }}>
                                    Get Started
                                </Text>
                                <AntDesignIcons name="right"
                                style={{fontSize:18 , color: COLORS.white,opacity:0.3 , marginLeft: SIZES.base}}/>
                                <AntDesignIcons
                                name="right"
                                style={{fontSize:25 , color: COLORS.white,marginLeft: -10}}
                                />
                            </TouchableOpacity>

                        )
                    }
                 
                   
                    

                </View>
            </SafeAreaView>


        )
    }

    //Get the images from the data and putting and desinging the image section
    const renderFlatlistItem = ({item}) => {
        return (
            <View style={{
                width: SIZES.width,
                flex: 1,
                alignItems: 'center',
                justifyContent: 'center'
            }}>
                <View style={{
                    alignItems: 'center',
                    marginVertical: SIZES.base * 2
                }}>
                    <ImageBackground
                    source={item.img}
                    
                    style={{width: 200, height: 200, resizeMode: 'stretch'}}
                    />
                </View>
                <View style={{paddingHorizontal: SIZES.base * 4, marginVertical: SIZES.base * 4}}>
                    <Text style={{fontSize: 30, textAlign: 'center', fontWeight: 'bold'}}>
                        {item.title}
                    </Text>
                    <Text style={{
                        fontSize: 18,
                        opacity: 0.4,
                        textAlign: 'center',
                        marginTop: 15,
                        lineHeight: 28
                    }}>
                        {item.description}
                    </Text>
                </View>

            </View>
        )
    }

    return(
        <View style={{
            flex: 1,
            backgroundColor: COLORS.background,
            justifyContent: 'center'
        }}>
            <StatusBar barStyle="dark-content" backgroundColor={COLORS.background} />
            
            {/* top section - back and skip button */}
            {renderTopSection()}



            {/* flatlistwith pages*/}
            <FlatList
            data={data}
            pagingEnabled
            horizontal
            showsHorizontalScrollIndicator={false}
            keyExtractor={item => item._id}
            renderItem={renderFlatlistItem}

            ref={flatlistRef}
            onViewableItemsChanged={handleViewableItemsChanged.current}
            viewabilityConfig={{viewAreaCoveragePercentThreshold: 100}}
            initialNumToRender={1}
            extraData={SIZES.width}
            />



            {/* butto section - pagination and next or getstared btton*/}
            {renderBottomSection()}
        </View>
    )
}

export default Onboarding