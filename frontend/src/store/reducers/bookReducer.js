const bookReducer = (state = [], action) => {
    switch(action.type){
        case "ADD_BOOK":
            return [...state, action.payload];
        case "FIND_BOOK":
        case "GENRE_BOOK":
        case "MAKE_TEXT":
        case "MAKE_AUDIO":
            return [action.payload];
        default:
            return state;
    }
}
export default bookReducer;