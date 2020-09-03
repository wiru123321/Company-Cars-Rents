import React from "react";
import { configure, render, mount, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";

configure({ adapter: new Adapter() });

import CarInfo from "../CarInfo";

describe("Car info test suite", () => {
  const emptyCar = {
    mark: "",
    model: "",
    licensePlate: "",
    capacity: "",
    mileage: "",
    year: "",
    hp: "",
  };

  const car = {
    mark: "Fiat",
    model: "Panda",
    licensePlate: "SL-1337",
    capacity: "5",
    mileage: "165000",
    year: "1995",
    hp: "245",
  };

  it("should render without throwing error", () => {
    expect(shallow(<CarInfo car={emptyCar} />));
  });

  it("should contain passed car informations", () => {
    const { mark, model, licensePlate, capacity, mileage, year, hp } = car;
    const wrapper = mount(<CarInfo car={car} />);
    // snapshots
    // expect(wrapper.contains()).to.equal(true);
  });
});
