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
} from "../../../../features/add-car-info/carsInfoSlice";
import {
  fetchMarks,
  fetchTypes,
  fetchFuelType,
  fetchColor,
  fetchGearboxType,
} from "../../../../features/starting-car-parameter/startingCarParameterSlice";
import { useDispatch } from "react-redux";
import { ValidatorForm } from "react-material-ui-form-validator";

const AddNewCar = () => {
  const dispatch = useDispatch();
  function addCar() {
    dispatch(reset());
  }
  return (
    <Cointainer fixed>
      <Typography
        component="div"
        style={{ height: "81vh", width: "100%" }}
        onLoad={dispatch(fetchMarks())}
        onLoad={dispatch(fetchTypes())}
        onLoad={dispatch(fetchFuelType())}
        onLoad={dispatch(fetchColor())}
        onLoad={dispatch(fetchGearboxType())}
      >
        <ValidatorForm
          onSubmit={addCar}
          style={{ width: "50vw", margin: "auto" }}
        >
          <div style={{ height: "2vh" }}></div>
          <AddCarForm />
        </ValidatorForm>
      </Typography>
    </Cointainer>
  );
};

export default AddNewCar;
