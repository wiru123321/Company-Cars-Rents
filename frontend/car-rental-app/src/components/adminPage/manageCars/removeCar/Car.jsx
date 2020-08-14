import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import CarInfo from "../../../carsListing/CarInfo";
import CarImage from "../../../carsListing/CarImage";
import CarControlPanel from "./CarControlPanel";
import UpdateCars from "../updateCars/UpdateCars";
import Issues from "./carIssues/IssuesList";
import { Grid, Paper } from "@material-ui/core";
import {
  fetchFaultsForCar,
  deleteFault,
} from "../../../../services/FaultsService";

const Car = ({ car, index, onDelete, toggleSuspend }) => {
  const dispatch = useDispatch();
  const [edit, setEdit] = useState(false);
  const [showIssues, setShowIssues] = useState(false);
  const [faults, setFaults] = useState([]);

  const toggleEdit = () => {
    if (edit === false) {
      setShowIssues(false);
    }
    setEdit(!edit);
  };

  const toggleShowIssues = () => {
    if (showIssues === false) {
      setEdit(false);
    }
    setShowIssues(!showIssues);
  };

  const handleFaultDelete = (fault) => {
    deleteFault(fault, car.licensePlate, setFaults);
  };

  const fetchFaults = () => {
    fetchFaultsForCar(car.licensePlate, setFaults);
  };

  const onSuspendToggle = () => {
    toggleSuspend(car.licensePlate, car, car.isActive);
  };

  useEffect(() => {
    fetchFaults();
  }, []);

  const switchView = () => {
    if (edit || showIssues) {
      if (edit) {
        return <UpdateCars car={car} />;
      } else if (showIssues) {
        return (
          <Issues
            fetchFaults={fetchFaults}
            handleFaultDelete={handleFaultDelete}
            faults={faults}
          />
        );
      }
    } else {
      return (
        <Paper>
          <Grid
            style={{ minHeight: "40vh", minWidth: "30vw" }}
            container
            justify="center"
            alignItems="center"
          >
            <Grid item>
              <CarImage
                src={
                  "http://localhost:8080/u/car/download-car-image/" +
                  car.licensePlate
                }
              />
            </Grid>
            <Grid item>
              <CarInfo car={car} />
            </Grid>
          </Grid>
        </Paper>
      );
    }
  };

  return (
    <Grid
      style={{ marginTop: "1%" }}
      container
      justify="center"
      alignItems="center"
    >
      <Paper>
        <Grid
          style={{ padding: "8px", minHeight: "50vh", minWidth: "50vw" }}
          container
          direction="column"
          justify="center"
          alignItems="center"
        >
          <Grid item style={{ minHeight: "40vh", minWidth: "40vw" }}>
            {switchView()}
          </Grid>
          <Grid item>
            <Paper style={{ padding: "8px", marginTop: "1%" }}>
              <Grid
                style={{ minWidth: "30vw" }}
                container
                justify="space-evenly"
                alignItems="center"
              >
                <CarControlPanel
                  index={index}
                  isActive={car.isActive}
                  toggleSuspend={onSuspendToggle}
                  onDelete={onDelete}
                  toggleEdit={toggleEdit}
                  toggleShowIssues={toggleShowIssues}
                  faults={faults}
                />
              </Grid>
            </Paper>
          </Grid>
        </Grid>
      </Paper>
    </Grid>
  );
};

export default Car;
/**<Grid container justify="center" alignItems="center">
{edit || showIssues ? (
  <Grid item xs={8}>
    {edit && <UpdateCars car={car} />}
    {showIssues && (
      <Issues
        fetchFaults={fetchFaults}
        handleFaultDelete={handleFaultDelete}
        faults={faults}
      />
    )}
  </Grid>
) : (
  <>
    <Grid item xs={4}>
      <CarImage
        src={
          "http://localhost:8080/u/car/download-car-image/" +
          car.licensePlate
        }
      />
    </Grid>
    <Grid item xs={4}>
      <CarInfo car={car} />
    </Grid>
  </>
)}

<Grid item xs={4}>
  <CarControlPanel
    index={index}
    onDelete={onDelete}
    toggleEdit={toggleEdit}
    toggleShowIssues={toggleShowIssues}
    faults={faults}
  />
</Grid>
</Grid> */
