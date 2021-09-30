const bookReducer = (state = [], action) => {
    switch(action.type){
        case "ADD_BOOK":
            return [...state, action.payload];
        case "FIND_BOOK":
        case "GENRE_BOOK":
        case "MAKE_TEXT":
        case "MAKE_AUDIO":
        case "ADD_LIKE":
        case "DELETE_LIKE":
        case "GET_LIKE":
        case "GET_TEXT":
        case "GET_AUDIO":  
        case "GET_READ":  
            return [action.payload];
        default:
            return state;
    }
}
export default bookReducer;