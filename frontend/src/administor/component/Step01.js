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

const Step01 = () => {
    return (
        <div>
            <h2>책 표지를 선택해주세요</h2>
            
            <Btn>사진 선택</Btn>
        </div>
    );
}
export default Step01;