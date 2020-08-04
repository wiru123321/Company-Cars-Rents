import React from "react";
import { Container, Button } from "../../styles/styles.style";
const Submit = (props) => {
  return (
    <Container width="100%" margin="1%">
      <Button width="30%" bgr="#f3f169" text="black" round>
        {props.children}
      </Button>
    </Container>
  );
};

export default Submit;
