import Spinner from "./Spinner.js";

const Loading = ({title}) => {
    return (
        <>
            <h2>{title}</h2>
            <Spinner></Spinner>
        </>
    );
};

export default Loading;