import React, { useEffect } from "react";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import { Grid, Button, Box, ListItem, List } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import {
  selectCars,
  chooseCar,
  toggleChoose,
  dateIsChoosenHandler,
  isCarFormActiveHandler,
} from "../../../features/car-reservation/reservationSlice";

const CarsSelection = () => {
  const dispatch = useDispatch();
  const cars = useSelector(selectCars);

  function toggleDismiss() {
    dispatch(toggleChoose());
  }
  function toggleSuggest(index) {
    dispatch(chooseCar(index));
    dispatch(toggleChoose());
  }

  const toggleCarChoose = () => {
    dispatch(dateIsChoosenHandler());
    dispatch(isCarFormActiveHandler());
  };

  return (
    <Grid container direction="column" justify="center" alignItems="center">
      <Button onClick={toggleCarChoose} variant="contained" color="secondary">
        Back To reservation form.
      </Button>
      <List>
        {cars.map((car, index) => (
          <ListItem key={index}>
            <Box display="flex">
              <CarImage
                src={
                  "http://localhost:8080/u/car/download-car-image/" +
                  car.licensePlate
                }
              />
              <CarInfo car={car} />
              <div>
                <Button
                  variant="contained"
                  onClick={() => toggleSuggest(index)}
                >
                  Select
                </Button>
              </div>
            </Box>
          </ListItem>
        ))}
      </List>
    </Grid>
  );
};

export default CarsSelection;
