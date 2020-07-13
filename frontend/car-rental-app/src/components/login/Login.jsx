import React, { useState } from "react";
import { Container } from "../../styles/styles.style";
import InputControl from "./InputControl";
import Submit from "./Submit";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  return (
    <Container width="40vw" height="40vh" bgr="#0e153a" col round>
      <InputControl type="login">Login</InputControl>
      <InputControl type="password">Password</InputControl>
      <Submit>Login</Submit>
    </Container>
  );
};

export default Login;
