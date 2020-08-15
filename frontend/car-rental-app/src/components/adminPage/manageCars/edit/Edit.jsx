import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid } from "@material-ui/core";
import {
  updateCar,
  selectAll,
  uploadPicture,
} from "../../../../features/cars-manager/carsManagerSlice";
import EditForm from "./EditForm";
import axios from "axios";
const API_URL = "http://localhost:8080";
const Edit = () => {
  const dispatch = useDispatch();
  const { currentCar, filterActive } = useSelector(selectAll);
  const [mileage, setMileage] = useState(currentCar.mileage);
  const [lastInspection, setLastInspection] = useState(
    currentCar.lastInspection.slice(0, 10)
  );
  const [photo, setPhoto] = useState("");

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

  const handlePhotoChange = (event) => {
    setPhoto(event.target.files[0]);
  };

  const handlePhotoUpdate = (event) => {
    event.preventDefault();

    dispatch(uploadPicture(currentCar.licensePlate, photo, filterActive));
  };

  return (
    <Grid container justify="center">
      <EditForm
        photo={photo}
        mileage={mileage}
        lastInspection={lastInspection}
        handleMileageChange={handleMileageChange}
        handleLastInspectionChange={handleLastInspectionChange}
        handlePhotoChange={handlePhotoChange}
        handleSubmit={handleSubmit}
        handlePhotoUpdate={handlePhotoUpdate}
      />
    </Grid>
  );
};

export default Edit;
