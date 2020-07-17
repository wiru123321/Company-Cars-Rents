import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  Container,
  TextField,
  Grid,
  Input,
  InputLabel,
  Button,
  Box,
  ListItem,
} from "@material-ui/core";
import CarsList from "../../carsListing/CarsList";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import {
  selectCars,
  selectCar,
  selectIsChoosen,
  selectChoose,
  chooseCar,
  undoChoose,
  toggleChoose,
} from "../../../features/car-reservation/reservationSlice";

const SelectedCar = () => {
  const dispatch = useDispatch();
  const isChoosen = useSelector(selectIsChoosen);
  const car = useSelector(selectCar);

  const toggleCarChoose = () => dispatch(toggleChoose());

  const SuggestButton = () => (
    <Button onClick={toggleCarChoose}>Suggest a car</Button>
  );

  const Car = () => (
    <div>
      <Box display="flex">
        <CarImage src={car.src} />
        <CarInfo car={car} />
      </Box>
      <Button onClick={toggleCarChoose}>Change car</Button>
    </div>
  );

  if (!isChoosen) {
    return <SuggestButton />;
  } else {
    return <Car />;
  }
};

const CarSuggestion = () => {
  const dispatch = useDispatch();
  const choose = useSelector(selectChoose);
  const cars = useSelector(selectCars);

  return (
    <Grid container direction="column" justify="center" alignItems="center">
      {choose ? (
        <div>
          <Button onClick={() => dispatch(toggleChoose())}>Dismiss</Button>
          {cars.map((car, index) => (
            <ListItem key={car.src}>
              <Box display="flex">
                <CarImage src={car.src} />
                <CarInfo car={car} />
                <Button
                  onClick={() => {
                    dispatch(chooseCar(index));
                    dispatch(toggleChoose());
                  }}
                >
                  Select
                </Button>
              </Box>
            </ListItem>
          ))}
        </div>
      ) : (
        <SelectedCar />
      )}
    </Grid>
  );
};

export default CarSuggestion;
