import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { Grid } from "@material-ui/core";
import { addFault } from "../../../features/cars-manager/carsManagerSlice";
import { useAlert } from "react-alert";
import AddIssuesForm from "../manageCars/issues/addIssues/AddIssuesForm";

const AddFaultDialog = ({ licensePlate }) => {
  const alert = useAlert();
  const [description, setDescription] = useState("");
  const [date, setDate] = useState("");
  const [setCarInactive, changeCarInactive] = useState(false);
  const dispatch = useDispatch();

  const handleReset = () => {
    setDescription("");
    changeCarInactive(false);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleDateChange = (event) => {
    setDate(event.target.value);
  };

  const handleCarActivityChange = (event) => {
    changeCarInactive(event.target.checked);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    let fault = {
      description: description,
      faultDate: date + "T00:00:00",
      setCarInactive: setCarInactive,
      carLicensePlate: licensePlate,
    };

    dispatch(addFault(fault, alert));
    handleReset();
  };

  return (
    <Grid container style={{ padding: "8px" }}>
      <AddIssuesForm
        description={description}
        date={date}
        setCarInactive={setCarInactive}
        handleDescriptionChange={handleDescriptionChange}
        handleDateChange={handleDateChange}
        handleCarInactiveChange={handleCarActivityChange}
        handleSubmit={handleSubmit}
      />
    </Grid>
  );
};
export default AddFaultDialog;
