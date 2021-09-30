const commentReducer = (state = [], action) => {
    switch(action.type){
        case "GET_COMMENT":
            return state;
        case "ADD_COMMENT":
            return [...state, action.payload];
        case "DELETE_COMMENT":
            return [state, []];
        case "MODIFY_COMMENT":
            return state;
        default:
            return state;
    }
}
export default commentReducer;