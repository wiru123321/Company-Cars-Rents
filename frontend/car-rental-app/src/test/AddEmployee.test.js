import React from "react";
import { configure, render, mount, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import AddEmployee from "../AddEmployee";
import UsersPersonalData from "../UsersPersonalData";
import UsersLogin from "../UsersLogin";
import UsersPassword from "../UsersPassword";
import { Provider } from "react-redux";
import store from "../store/store";

configure({ adapter: new Adapter() });

describe("sum two numbers", () => {
  it("should render component", () => {
    const wrapper = render(
      <Provider store={store}>
        <AddEmployee />
      </Provider>
    );
  });

  it("should change value", () => {
    const testState = { firstname: "", lastname: "" };

    const handleFirstnameChange = (event) => {
      testState.firstname = event.target.value;
    };

    const handleLastnameChange = (event) => {
      testState.lastname = event.target.value;
    };

    const wrapper = mount(
      <UsersPersonalData
        firstname={testState.firstname}
        lastname={testState.lastname}
        handleFirstnameChange={handleFirstnameChange}
        handleLastnameChange={handleLastnameChange}
      />
    );

    const firstnameInput = wrapper.find("input").at(0);
    const lastnameInput = wrapper.find("input").at(1);

    firstnameInput.simulate("change", {
      target: { name: "value", value: "Jan" },
    });
    lastnameInput.simulate("change", {
      target: { name: "value", value: "Kowalski" },
    });

    expect(testState.firstname).toEqual("Jan");
    expect(testState.lastname).toEqual("Kowalski");
  });

  it("should change value", () => {
    const testState = { email: "", login: "", phoneNumber: "" };
    const handleEmailChange = (event) => {
      testState.email = event.target.value;
    };

    const handleLoginChange = (event) => {
      testState.login = event.target.value;
    };

    const handlePhoneNumberChange = (event) => {
      testState.phoneNumber = event.target.value;
    };

    const wrapper = mount(
      <UsersLogin
        email={testState.email}
        login={testState.login}
        phoneNumber={testState.phoneNumber}
        handleEmailChange={handleEmailChange}
        handleLoginChange={handleLoginChange}
        handlePhoneNumberChange={handlePhoneNumberChange}
      />
    );

    const emailInput = wrapper.find("input").at(0);
    const loginInput = wrapper.find("input").at(1);
    const phoneNumberInput = wrapper.find("input").at(2);

    emailInput.simulate("change", {
      target: { name: "value", value: "jan.kowalski@wp.pl" },
    });
    loginInput.simulate("change", {
      target: { name: "value", value: "kowal" },
    });
    phoneNumberInput.simulate("change", {
      target: { name: "value", value: "123456789" },
    });

    expect(testState.email).toEqual("jan.kowalski@wp.pl");
    expect(testState.login).toEqual("kowal");
    expect(testState.phoneNumber).toEqual("123456789");
  });

  it("should change value", () => {
    const testState = { password: "", rePassword: "" };

    const handlePasswordChange = (event) => {
      testState.password = event.target.value;
    };

    const handleRePasswordChange = (event) => {
      testState.rePassword = event.target.value;
    };

    const wrapper = mount(
      <UsersPassword
        password={testState.password}
        rePassword={testState.rePassword}
        handlePasswordChange={handlePasswordChange}
        handleRePasswordChange={handleRePasswordChange}
      />
    );
    const passwordInput = wrapper.find("input").at(0);
    const rePasswordInput = wrapper.find("input").at(1);

    passwordInput.simulate("change", {
      target: { name: "value", value: "abcd1234" },
    });
    rePasswordInput.simulate("change", {
      target: { name: "value", value: "abcd1234" },
    });

    expect(testState.password).toEqual("abcd1234");
    expect(testState.rePassword).toEqual("abcd1234");
  });
});
