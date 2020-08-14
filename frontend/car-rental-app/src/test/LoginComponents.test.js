import React from "react";
import { configure, render, mount, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";

configure({ adapter: new Adapter() });

import InputControl from "../../../components/login/InputControl";

describe("Input control test suite", () => {
  it("should render withoout throwing error", () => {
    expect(shallow(<InputControl />));
  });

  it("should change state", () => {
    const testState = { value: "" };
    const handleChange = (value) => {
      testState.value = value;
    };

    const wrapper = mount(
      <InputControl value={testState.value} handleChange={handleChange} />
    );

    const passedValue = "login";

    const input = wrapper.find("input").at(0);

    input.simulate("change", { target: { name: "value", value: passedValue } });

    expect(testState.value).toEqual(passedValue);
  });
});
