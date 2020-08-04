import React from "react";
import { configure, render, mount, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import { UserPersonalData, ReservationDate } from "../ReservationForm";
configure({ adapter: new Adapter() });

describe("Reservation test suite", () => {
  it("should render without throwing error", () => {
    expect(shallow(<UserPersonalData />));
  });

  it("should render without throwing error", () => {
    expect(shallow(<ReservationDate />));
  });

  it("should handle value change", () => {
    const passedFname = "Jan";
    const passedLname = "Kowalski";
    const testState = { firstname: "", lastname: "" };

    const handleFirstnameChange = (event) => {
      testState.firstname = event.target.value;
    };

    const handleLastnameChange = (event) => {
      testState.lastname = event.target.value;
    };

    const wrapper = mount(
      <UserPersonalData
        handleFirstnameChange={handleFirstnameChange}
        handleLastnameChange={handleLastnameChange}
      />
    );

    const firstnameInput = wrapper.find("input").at(0);
    const lastnameInput = wrapper.find("input").at(1);

    firstnameInput.simulate("change", {
      target: { name: "value", value: passedFname },
    });
    lastnameInput.simulate("change", {
      target: { name: "value", value: passedLname },
    });

    expect(testState.firstname).toEqual(passedFname);
    expect(testState.lastname).toEqual(passedLname);
  });

  it("should handle value change", () => {
    const passedDate = "02-10-2020";
    const passedHour = "16-40";

    const testState = { date: "", hour: "" };

    const handleDateChange = (event) => {
      testState.date = event.target.value;
    };
    const handleHourChange = (event) => {
      testState.hour = event.target.value;
    };

    const wrapper = mount(
      <ReservationDate
        handleDateChange={handleDateChange}
        handleHourChange={handleHourChange}
      />
    );

    const dateInput = wrapper.find("input").at(0);
    const hourInput = wrapper.find("input").at(1);

    dateInput.simulate("change", {
      target: { name: "value", value: passedDate },
    });
    hourInput.simulate("change", {
      target: { name: "value", value: passedHour },
    });

    expect(testState.date).toEqual(passedDate);
    expect(testState.hour).toEqual(passedHour);
  });
});
