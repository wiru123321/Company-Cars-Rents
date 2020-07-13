const { default: styled } = require("styled-components");

const onHover = `
background-color: #e2f3f5;
color: black;`;

export const Container = styled.div`
  display: flex;
  ${(props) => (props.col ? `flex-direction: column;` : ``)}
  justify-content: space-evenly;
  align-items: center;
  ${(props) => (props.height ? `height: ${props.height};` : ``)}
  ${(props) => (props.width ? `width: ${props.width};` : ``)}
  ${(props) => (props.margin ? `margin: ${props.margin};` : ``)}
  ${(props) =>
    props.bgr
      ? `background-color: ${props.bgr};`
      : `background-color: inherit;`}
  ${(props) => (props.round ? `border-radius: 20px;` : ``)}
`;

export const Label = styled.label`
  font-size: 1.5rem;
  text-align: left;
  color: #e2f3f5;
`;

export const Input = styled.input`
  ${(props) => (props.width ? `width: ${props.width};` : ``)}
  margin-top: 2%;
  font-size: 1.5rem;
  border: none;
  padding: 8px;
  background-color: #3d5af1;
  color: #e2f3f5;
  ${(props) => (props.padding ? `padding: ${props.padding};` : ``)}
  ${(props) => (props.round ? `border-radius: 20px;` : ``)}
`;

export const Button = styled.button`
  font-size: 1.3rem;
  border: 3px;
  padding: 8px;
  ${(props) => (props.width ? `width: ${props.width};` : ``)}
  ${(props) => (props.padding ? `padding: ${props.padding};` : ``)}
  ${(props) => (props.round ? `border-radius: 20px;` : ``)}
  ${(props) => (props.text ? `color: ${props.text};` : `color: black;`)}
  ${(props) =>
    props.bgr ? `background-color: ${props.bgr};` : `background-color: white;`}
    &:hover {
      ${onHover}
    }  
`;
