import React from "react";
import { Container, Button } from "../../styles/styles.style";
const Submit = (props) => {
  return (
    <Container width="100%" margin="1%">
      <Button width="30%" bgr="#3d5af1" text="#e2f3f5" round>
        {props.children}
      </Button>
    </Container>
  );
};

export default Submit;
