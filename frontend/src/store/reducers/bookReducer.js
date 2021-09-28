const bookReducer = (state = [], action) => {
    switch(action.type){
        case "ADD_BOOK":
            return [...state, action.payload];
        case "FIND_BOOK":
            return state;
        case "GENRE_BOOK":
            return state;
        default:
            return state;
    }
}
export default bookReducer;