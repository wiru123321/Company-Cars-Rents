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

    //const blob = new Blob(JSON.stringify(photo, null, 2));

    let formData = new FormData();
    formData.append("imageFile", photo);
    console.log(photo);
    console.log(formData.get("imageFile"));
    axios
      .post(
        API_URL + `/a/car/upload-car-image/${currentCar.licensePlate}`,
        formData,
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
            "content-type": "multipart/form-data",
          },
        }
      )
      .then((response) => {
        console.log(response);
      })
      .catch((error) => console.log(JSON.stringify(error)));
    //dispatch(uploadPicture(currentCar.licensePlate, photo, filterActive));
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
