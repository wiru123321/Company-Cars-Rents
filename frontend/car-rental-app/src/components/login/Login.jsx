import React, { useState } from "react";
import { Container } from "../../styles/styles.style";
import InputControl from "./InputControl";
import Submit from "./Submit";

const Error = ({ message }) => {
  return <div style={{ color: "red" }}> {message}</div>;
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

export default Login;
