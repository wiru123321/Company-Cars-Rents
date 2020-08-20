import React from "react";
import { useDispatch } from "react-redux";
import { Grid, Paper, Button, makeStyles } from "@material-ui/core";
import CarInfoCard from "../../activeRents/resrvationUi/CarInfoCard";
import {
  setCurrentCar,
  enterManageCarMode,
} from "../../../../features/cars-manager/carsManagerSlice";

const useStyles = makeStyles({
  content: {
    padding: "8px",
    marginTop: "1%",
    minWidth: "40vw",
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
    <Paper elevation={6} className={classes.content}>
      <Grid container justify="space-evenly" alignItems="center">
        <CarInfoCard car={car} />
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
