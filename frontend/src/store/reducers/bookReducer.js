const bookReducer = (state = [], action) => {
    switch(action.type){
        case "ADD_BOOK":
            return [...state, action.payload];
        default:
            return state;
    }
}
export default bookReducer;