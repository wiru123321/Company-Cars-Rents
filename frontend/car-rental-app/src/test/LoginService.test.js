import React from "react";
import axios from "axios";
import { configure, render, mount, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import { handleLogin } from "../LoginService";
configure({ adapter: new Adapter() });

describe("Login test suite", () => {
  it("should return resposne", () => {
    const user = { login: "admin123", password: "apassword123" };
    handleLogin(user)
      .then((response) => {
        console.log("123");
        console.log(response.data);
      })
      .catch((error) => {
        console.log("err");
      });
  });
});
