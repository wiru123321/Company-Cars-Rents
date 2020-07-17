import React from "react";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import { Grid, Button, Box, ListItem, List } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import {
  selectCars,
  chooseCar,
  toggleChoose,
} from "../../../features/car-reservation/reservationSlice";

const CarsSelection = () => {
  const dispatch = useDispatch();
  const cars = useSelector(selectCars);
  return (
    <Grid container direction="column" justify="center" alignItems="center">
      <Button
        onClick={() => dispatch(toggleChoose())}
        variant="contained"
        color="secondary"
      >
        Dismiss
      </Button>
      <List>
        {cars.map((car, index) => (
          <ListItem key={car.src}>
            <Box display="flex">
              <CarImage src={car.src} />
              <CarInfo car={car} />
              <div>
                <Button
                  variant="contained"
                  onClick={() => {
                    dispatch(chooseCar(index));
                    dispatch(toggleChoose());
                  }}
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
