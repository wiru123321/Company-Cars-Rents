import React from "react";
import { configure, render, mount, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";

configure({ adapter: new Adapter() });

import CarImage from "../components/carsListing/CarImage";

describe("Car image test suite", () => {
  it("should render without throwing error", () => {
    expect(shallow(<CarImage />));
  });

  it("should render alt without throwing error", () => {
    expect(shallow(<CarImage src={""} />));
  });
});
