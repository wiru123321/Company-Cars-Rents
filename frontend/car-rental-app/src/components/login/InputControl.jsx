import React from "react";
import { Container, Input, Label } from "../../styles/styles.style";
const InputControl = ({ children, type, value, handleChange }) => {
  return (
    <Container width="100%" col margin="1%">
      <Label>{children}</Label>
      <Input
        value={value}
        placeholder="..."
        onChange={(event) => {
          handleChange(event.target.value);
        }}
        width="50%"
        round
        type={type}
      />
    </Container>
  );
};
export default InputControl;
