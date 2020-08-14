import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid } from "@material-ui/core";
import EditForm from "./EditForm";

import {
  fetchCars,
  updateCar,
  selectAll,
} from "../../../../features/cars-manager/carsManagerSlice";

const Edit = () => {
  const dispatch = useDispatch();
  const { currentCar, filterActive } = useSelector(selectAll);
  const [mileage, setMileage] = useState(currentCar.mileage);
  const [lastInspection, setLastInspection] = useState(
    currentCar.lastInspection.slice(0, 10)
  );

  const handleMileageChange = (event) => {
    setMileage(event.target.value);
  };

  const handleLastInspectionChange = (event) => {
    setLastInspection(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    let newCarDTO = {
      ...currentCar,
      mileage: mileage,
      lastInspection: lastInspection + "T00:00:00",
    };
    dispatch(updateCar(currentCar.licensePlate, newCarDTO, filterActive));
  };
  const handlePhotoUpdate = () => {};

  return (
    <Grid container justify="center">
      <EditForm
        mileage={mileage}
        lastInspection={lastInspection}
        handleMileageChange={handleMileageChange}
        handleLastInspectionChange={handleLastInspectionChange}
        handleSubmit={handleSubmit}
        handlePhotoUpdate={handlePhotoUpdate}
      />
    </Grid>
  );
};

export default Edit;
