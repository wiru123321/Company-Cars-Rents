import React from "react";
import SelectedCar from "./SelectedCar";
import CarsSelection from "./CarsSelection";
import { useSelector } from "react-redux";
import useStyles from "./useStyles";
import { Grid } from "@material-ui/core";
import { selectChoose } from "../../../features/car-reservation/reservationSlice";

const CarSuggestion = () => {
  const classes = useStyles();
  const choose = useSelector(selectChoose);

  return (
    <Grid className={classes.listRender} container>
      {choose ? <CarsSelection /> : <SelectedCar />}
    </Grid>
  );
};

export default CarSuggestion;
