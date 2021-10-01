const userReducer = (state = {}, action) => {
    switch(action.type){
        case "ADD_USER":
            return state;
        case "LOGIN_USER":
            return [action.payload];
        case "LOGOUT_USER":
            return '';
        default:
            return state;
    }
}
export default userReducer;