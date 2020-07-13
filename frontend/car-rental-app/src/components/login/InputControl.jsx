import React from "react";
import { Container, Input, Label } from "../../styles/styles.style";
const InputControl = ({ children, type }) => {
  return (
    <Container width="100%" col margin="1%">
      <Label>{children}</Label>
      <Input width="50%" round type={type} />
    </Container>
  );
};
export default InputControl;
