import React, { useState } from "react";
import { Container, Input, Label, Button } from "../../styles/styles.style";

const Username = () => {
  return (
    <Container width="100%" col>
      <Label>Username</Label>
      <Input width="50%" round type="text" />
    </Container>
  );
};

const Password = () => {
  return (
    <Container width="100%" col margin="1%">
      <Label>Password</Label>
      <Input width="50%" round type="password" />
    </Container>
  );
};

const Submit = (props) => {
  return (
    <Container width="100%" margin="1%">
      <Button width="30%" bgr="#3d5af1" text="#e2f3f5" round>
        {props.children}
      </Button>
    </Container>
  );
};

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  return (
    <Container width="40vw" height="40vh" bgr="#0e153a" col round>
      <Username />
      <Password />
      <Submit>Login</Submit>
    </Container>
  );
};

export default Login;
