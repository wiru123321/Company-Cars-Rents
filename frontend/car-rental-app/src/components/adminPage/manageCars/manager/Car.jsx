import React from "react";
import { useDispatch } from "react-redux";
import { Grid, Paper, Button, makeStyles } from "@material-ui/core";
import CarImage from "../../../carsListing/CarImage";
import CarInfo from "../../../carsListing/CarInfo";
import {
  setCurrentCar,
  enterManageCarMode,
} from "../../../../features/cars-manager/carsManagerSlice";

const useStyles = makeStyles({
  content: {
    padding: "8px",
    marginTop: "1%",
    width: "50vw",
  },
  maxWidthButton: {
    minWidth: "100%",
  },
});

const Car = ({ car }) => {
  const classes = useStyles();
  const dispatch = useDispatch();

  const handleManage = () => {
    dispatch(setCurrentCar(car));
    dispatch(enterManageCarMode(true));
  };

  return (
    <Paper className={classes.content}>
      <Grid container justify="space-evenly" alignItems="center">
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
      <Grid container justify="center" alignItems="center">
        <Button
          color="primary"
          variant="outlined"
          onClick={handleManage}
          className={classes.maxWidthButton}
        >
          Manage
        </Button>
      </Grid>
    </Paper>
  );
};

export default Car;
