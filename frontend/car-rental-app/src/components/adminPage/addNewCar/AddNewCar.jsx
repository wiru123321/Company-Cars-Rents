import React from "react";

import Cointainer from "@material-ui/core/Container";
import Typography from "@material-ui/core/Typography";
import AddCarForm from "./AddCarForm";
import {
  brandChange,
  typeChange,
  licencePlateChange,
  fuelTypeChange,
  yearChange,
  milageChange,
  hpChange,
  peopleCapacityChange,
  doorsNumberChange,
  colorChange,
  gearboxTypeChange,
  trunkCapacityChange,
  imageUrlChange,
  reset,
  addCar,
} from "../../../features/add-car-info/carsInfoSlice";
import {
  fetchMarks,
  fetchTypes,
  fetchFuelType,
  fetchColor,
  fetchGearboxType,
} from "../../../features/starting-car-parameter/startingCarParameterSlice";
import { useDispatch } from "react-redux";

const AddNewCar = () => {
  const dispatch = useDispatch();
  function addCar() {
    dispatch(reset());
  }
  return (
    <Cointainer fixed>
      <Typography
        component="div"
        style={{ backgroundColor: "#cfe8fc", height: "94.6vh", width: "100%" }}
        onLoad={dispatch(fetchMarks())}
        onLoad={dispatch(fetchTypes())}
        onLoad={dispatch(fetchFuelType())}
        onLoad={dispatch(fetchColor())}
        onLoad={dispatch(fetchGearboxType())}
      >
        <form style={{ width: "50vw", margin: "auto" }} onSubmit={addCar}>
          <div style={{ height: "5vh" }}></div>
          <AddCarForm />
        </form>
      </Typography>
    </Cointainer>
  );
};

export default AddNewCar;
