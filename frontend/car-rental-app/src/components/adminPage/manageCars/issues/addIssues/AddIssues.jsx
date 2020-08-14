import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid } from "@material-ui/core";
import AddIssuesForm from "./AddIssuesForm";
import {
  selectAll,
  addFault,
} from "../../../../../features/cars-manager/carsManagerSlice";

const AddIssues = () => {
  const dispatch = useDispatch();
  const { currentCar, filterActive } = useSelector(selectAll);
  const [description, setDescription] = useState("");
  const [setCarInactive, changeCarInactive] = useState(false);

  const resetForm = () => {
    setDescription("");
    changeCarInactive(false);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleCarInactiveChange = (event) => {
    changeCarInactive(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    dispatch(
      addFault(
        {
          description,
          setCarInactive,
          carLicensePlate: currentCar.licensePlate,
        },
        filterActive
      )
    );
    resetForm();
  };

  return (
    <Grid container justify="center">
      <AddIssuesForm
        description={description}
        setCarInactive={setCarInactive}
        handleDescriptionChange={handleDescriptionChange}
        handleCarInactiveChange={handleCarInactiveChange}
        handleSubmit={handleSubmit}
      />
    </Grid>
  );
};

export default AddIssues;
