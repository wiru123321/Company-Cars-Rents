import React from "react";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import { Grid, Button, Box, ListItem, List, Paper } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import {
  selectCars,
  chooseCar,
  setisEndOfForm,
} from "../../../features/car-reservation/reservationSlice";

const CarsSelection = () => {
  const dispatch = useDispatch();
  const cars = useSelector(selectCars);
  let btnColor = "";

  function toggleSuggest(index) {
    dispatch(chooseCar(index));
    dispatch(setisEndOfForm());
  }

  return (
    <Grid container direction="column" justify="center" alignItems="center">
      <List>
        {cars.map((car, index) => (
          <Paper elevation={6} style={{ marginBottom: "1vh" }}>
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
                    color={btnColor}
                    onClick={() => toggleSuggest(index)}
                  >
                    Select
                  </Button>
                </div>
              </Box>
            </ListItem>
          </Paper>
        ))}
      </List>
    </Grid>
  );
};

export default CarsSelection;
