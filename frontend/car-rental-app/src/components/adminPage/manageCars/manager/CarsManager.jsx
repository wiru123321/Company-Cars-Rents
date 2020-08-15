import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid, makeStyles } from "@material-ui/core";
import {
  selectAll,
  fetchCars,
  filterCars,
} from "../../../../features/cars-manager/carsManagerSlice";
import NotFound from "../../messages/NotFoundMessage";
import Car from "./Car";
import CarMenu from "./CarMenu";
import Search from "../searchbar/Search";

const useStyles = makeStyles({
  content: {
    minHeight: "80vh",
  },
});

const CarsManager = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const {
    cars,
    filteredCars,
    filterActive,
    manageCarMode,
    filterLicensePlate,
    filterMark,
  } = useSelector(selectAll);

  useEffect(() => {
    dispatch(fetchCars(filterActive));
  }, [filterActive]);

  useEffect(() => {
    dispatch(filterCars(cars, filterLicensePlate, filterMark));
  }, [cars, filterLicensePlate, filterMark]);

  if (manageCarMode) {
    return (
      <Grid
        className={classes.content}
        container
        direction="column"
        alignItems="center"
      >
        <CarMenu />
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
        <Search />
        {filteredCars.length > 0 ? (
          filteredCars.map((car, index) => (
            <Car key={car.licensePlate} car={car} />
          ))
        ) : (
          <NotFound>Empty</NotFound>
        )}
      </Grid>
    );
  }
};

export default CarsManager;
