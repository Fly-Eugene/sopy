import React from 'react';

const Btn = styled.button`
  margin-left: auto;
  word-break: break-all;
  font-size: 16px;
  line-height: 1;
  font-weight: 400;
  color: #ffdac7;
  border-radius: 15px; 
`;

const button = ({
    text
}) => {
    return (
        <Btn>text</Btn>
    );
}
export default button;