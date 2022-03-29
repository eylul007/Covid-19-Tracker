import { Dimensions } from "react-native";
const {width, height} = Dimensions.get('window');

// Initalize the primary color of buttons,text,height and width onboarging screens
export const COLORS = {
    primary : "#32afb6",
    black: "#171717",
    white: "#FFFFFF",
    background: "#FFFFFF"
}

export const SIZES = {
    base: 10,
    width,
    height
}

const theme = { COLORS, SIZES };
export default theme;