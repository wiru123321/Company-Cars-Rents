import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid, makeStyles } from "@material-ui/core";
import {
  selectAll,
  fetchCars,
} from "../../../features/cars-manager/carsManagerSlice";
import Car from "./Car";
import CarManager from "./CarManager";

const useStyles = makeStyles({
  content: {
    minHeight: "80vh",
  },
});

const CarsSearchBar = () => {};

const CarsManager = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { cars, filteredCars, filterActive, manageCarMode } = useSelector(
    selectAll
  );

  useEffect(() => {
    dispatch(fetchCars(filterActive));
  }, []);

  if (manageCarMode) {
    return (
      <Grid
        className={classes.content}
        container
        direction="column"
        alignItems="center"
      >
        <CarManager />
      </Grid>
    );
  } else {
    return (
      <Grid
        className={classes.content}
        container
        direction="column"
        alignItems="center"
      >
        {filteredCars.map((car, index) => (
          <Car key={car.licensePlate} car={car} />
        ))}
      </Grid>
    );
  }
};

export default CarsManager;
