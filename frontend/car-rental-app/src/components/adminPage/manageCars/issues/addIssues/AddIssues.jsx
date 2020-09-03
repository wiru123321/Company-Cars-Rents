import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid } from "@material-ui/core";
import AddIssuesForm from "./AddIssuesForm";
import {
  selectAll,
  addFault,
} from "../../../../../features/cars-manager/carsManagerSlice";
import { useAlert } from "react-alert";

const AddIssues = () => {
  const alert = useAlert();
  const dispatch = useDispatch();
  const { currentCar, filterActive } = useSelector(selectAll);
  const [description, setDescription] = useState("");
  const [date, setDate] = useState("");
  const [setCarInactive, changeCarInactive] = useState(false);

  const resetForm = () => {
    setDescription("");
    changeCarInactive(false);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleDateChange = (event) => {
    setDate(event.target.value);
  };

  const handleCarInactiveChange = (event) => {
    changeCarInactive(event.target.checked);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    dispatch(
      addFault(
        {
          description,
          setCarInactive,
          faultDate: date + "T00:00:00",
          carLicensePlate: currentCar.licensePlate,
        },
        alert,
        filterActive
      )
    );
    resetForm();
  };

  return (
    <Grid container justify="center">
      <AddIssuesForm
        description={description}
        date={date}
        setCarInactive={setCarInactive}
        handleDescriptionChange={handleDescriptionChange}
        handleDateChange={handleDateChange}
        handleCarInactiveChange={handleCarInactiveChange}
        handleSubmit={handleSubmit}
      />
    </Grid>
  );
};

export default AddIssues;
