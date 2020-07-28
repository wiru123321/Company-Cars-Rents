import React from "react";
import { render, fireEvent } from "@testing-library/react";
import "@testing-library/jest-dom/extend-expect";
import AddEmployee from "../AddEmployee";
import { Provider } from "react-redux";
import store from "../../../../store/store";

const TestComponent = (
  <Provider store={store}>
    <AddEmployee />
  </Provider>
);

test("renders component", async () => {
  const { getByText } = render(
    <Provider store={store}>
      <AddEmployee />
    </Provider>
  );
  expect(getByText("Create account")).toBeInTheDocument();
});

test("contains all elements", async () => {
  const { getByPlaceholderText } = render(
    <Provider store={store}>
      <AddEmployee />
    </Provider>
  );

  const firstname = expect(getByPlaceholderText("firstname"));
  const lastname = expect(getByPlaceholderText("lastname"));
  const email = expect(getByPlaceholderText("email"));
  const login = expect(getByPlaceholderText("login"));
  const phoneNumber = expect(getByPlaceholderText("phoneNumber"));
  const password = expect(getByPlaceholderText("password"));
  const repeatPassword = expect(getByPlaceholderText("repeatPassword"));

  /*fireEvent.change(firstname, { target: { value: "Jan" } });
  fireEvent.change(lastname, { target: { value: "Kowalski" } });
  fireEvent.change(email, { target: { value: "Jan.Kowalski@wp.pl" } });
  fireEvent.change(login, { target: { value: "jkowal222" } });
  fireEvent.change(phoneNumber, { target: { value: "123-456-789" } });
  fireEvent.change(password, { target: { value: "jkwlwl" } });
  fireEvent.change(repeatPassword, { target: { value: "jkwlwl" } });*/
  //expect()
});
