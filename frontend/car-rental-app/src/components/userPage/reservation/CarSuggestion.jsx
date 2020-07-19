import React from "react";
import SelectedCar from "./SelectedCar";
import CarsSelection from "./CarsSelection";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import { Grid } from "@material-ui/core";
import { selectChoose } from "../../../features/car-reservation/reservationSlice";

const useStyles = makeStyles((theme) => ({
  root: {
    minHeight: "20vh",
    padding: "8px",
  },
}));

const CarSuggestion = () => {
  const classes = useStyles();
  const choose = useSelector(selectChoose);

  return (
    <Grid className={classes.root} container>
      {choose ? <CarsSelection /> : <SelectedCar />}
    </Grid>
  );
};

export default CarSuggestion;
