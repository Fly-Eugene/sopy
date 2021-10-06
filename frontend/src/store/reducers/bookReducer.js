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
        case "ADD_BOOKMARK":
        case "GET_BOOKMARK":
        case "DELETE_BOOKMARK":  
            return [action.payload];
        default:
            return state;
    }
}
export default bookReducer;