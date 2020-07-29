import React from "react";
import { configure, render, mount } from "enzyme";
import { useSelector } from "react-redux";
import { selectAll } from "../../../../features/add-employees/addEmployeeSlice";
import Adapter from "enzyme-adapter-react-16";
import { shallow } from "enzyme";
import AddEmployee from "../AddEmployee";
import { Provider } from "react-redux";
import store from "../../../../store/store";
import { ExpansionPanelActions } from "@material-ui/core";

configure({ adapter: new Adapter() });

describe("sum two numbers", () => {
  it("should render component", () => {
    const wrapper = render(
      <Provider store={store}>
        <AddEmployee />
      </Provider>
    );
  });
  it("should change input", () => {
    const wrapper = mount(
      <Provider store={store}>
        <AddEmployee />
      </Provider>
    );

    //const input = wrapper.find("input").at(0);
  });
});
