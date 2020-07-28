import React from "react";
import { render } from "@testing-library/react";
import "@testing-library/jest-dom/extend-expect";
import AddEmployee from "../AddEmployee";
import { Provider } from "react-redux";
import store from "../../../../store/store";

test("renders component", async () => {
  const { getByText } = render(
    <Provider store={store}>
      <AddEmployee />
    </Provider>
  );
  expect(getByText("Create account")).toBeInTheDocument();
});
