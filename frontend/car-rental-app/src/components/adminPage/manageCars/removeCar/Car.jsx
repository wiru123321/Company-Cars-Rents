import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import CarInfo from "../../../carsListing/CarInfo";
import CarImage from "../../../carsListing/CarImage";
import CarControlPanel from "./CarControlPanel";
import UpdateCars from "../updateCars/UpdateCars";
import Issues from "./carIssues/IssuesList";
import { ListItem, Grid } from "@material-ui/core";
import { fetchFaultsForCar } from "../../../../features/car-management/carManagerSlice";

const Car = ({ car, index, onDelete }) => {
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

  const fetchFaults = () => {
    dispatch(fetchFaultsForCar(car.licensePlate, setFaults));
  };

  useEffect(() => {
    fetchFaults();
  }, []);

  return (
    <ListItem>
      <Grid container justify="center" alignItems="center">
        {edit || showIssues ? (
          <Grid item xs={8}>
            {edit && <UpdateCars car={car} faults={faults} />}
            {showIssues && <Issues fetchFaults={fetchFaults} faults={faults} />}
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
            isActive={car.isActive}
            index={index}
            onDelete={onDelete}
            toggleEdit={toggleEdit}
            toggleShowIssues={toggleShowIssues}
            faults={faults}
          />
        </Grid>
      </Grid>
    </ListItem>
  );
};

export default Car;
