import React, { useState } from "react";
import {
  Container,
  Paper,
  Divider,
  Button,
  Grid,
  Dialog,
  makeStyles,
} from "@material-ui/core";
import RequestedCarInfo from "../rentRequests/rentRequestInfo/RequestedCarInfo";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";

const API_URL = "http://localhost:8080";

const useStyles = makeStyles({
  paper: {
    padding: "8px",
    minHeight: "40vh",
    width: "50vw",
    maxHeight: "60vh",
    overflow: "auto",
  },
  active: {
    margin: "1%",
    padding: "8px",
    backgroundColor: "white",
    cursor: "pointer",
  },
  item: {
    margin: "1%",
    padding: "8px",
    backgroundColor: "grey",
    cursor: "pointer",
  },
  okButton: {
    width: "100%",
    padding: "4px",
  },
});

const ModalContent = ({
  cars,
  changeCurrentCar,
  handleClose,
  setActive,
  currentIndex,
}) => {
  const classes = useStyles();
  return (
    <Grid container>
      <Grid className={classes.paper}>
        {cars.map((car, index) => {
          return (
            <Paper
              className={currentIndex === index ? classes.active : classes.item}
              onClick={() => {
                changeCurrentCar(car);
                setActive(index);
              }}
            >
              <CarImage
                src={API_URL + "/u/car/download-car-image/" + car.licensePlate}
              />
              <CarInfo car={car} />
            </Paper>
          );
        })}
      </Grid>
      <Button onClick={handleClose} className={classes.okButton}>
        Ok
      </Button>
    </Grid>
  );
};

export default ModalContent;
