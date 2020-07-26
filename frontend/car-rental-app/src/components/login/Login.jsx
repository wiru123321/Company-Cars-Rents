import React, { useState } from "react";
import {
  Container,
  ShadowContainer,
  ImageContainer,
} from "../../styles/styles.style";
import InputControl from "./InputControl";
import Submit from "./Submit";

const Error = ({ message }) => {
  // TODO: Make separate component when integrated with Api.
  return <div style={{ color: "red" }}> {message}</div>;
};

const LoginPage = () => {
  return (
    <ImageContainer img>
      <ShadowContainer shadow="0.4">
        <Container col height="100vh">
          <Login />
        </Container>
      </ShadowContainer>
    </ImageContainer>
  );
};

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [validate, setValidate] = useState(false);

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        if (username && password) {
          alert("logged in");
        }
        setValidate(true);
      }}
    >
      <Container width="40vw" height="40vh" bgr="#0e153a" col round>
        <InputControl type="login" value={username} handleChange={setUsername}>
          Login
        </InputControl>
        {validate && !username && <Error message="Please, type your login." />}
        <InputControl
          type="password"
          value={password}
          handleChange={setPassword}
        >
          Password
        </InputControl>
        {validate && !password && <Error message="Please, type a password." />}
        <Submit>Login</Submit>
      </Container>
    </form>
  );
};

export default LoginPage;
