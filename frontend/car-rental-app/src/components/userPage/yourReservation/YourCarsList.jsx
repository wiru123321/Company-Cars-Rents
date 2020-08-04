import React from "react";
import { Grid, Button, Box, ListItem, List } from "@material-ui/core";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import { useSelector, useDispatch } from "react-redux";
import {
  selectCars,
  chooseCar,
} from "../../../features/your-cars/yourCarsSlice";

const YourCarsList = () => {
  const dispatch = useDispatch();
  const cars = useSelector(selectCars);
  let ListStyle = "";
  return (
    <Grid container direction="column" justify="center" alignItems="center">
      <List>
        {cars.map((car, index) =>
          car.isEndOfRent ? (
            <ListItem key={car.src} style={{ backgroundColor: "#f56f42" }}>
              <Box display="flex">
                <CarImage src={car.src} />
                <CarInfo car={car} />
              </Box>
              <Button
                variant="outlined"
                color="primary"
                onClick={() => {
                  dispatch(chooseCar(index));
                }}
              >
                Give the car back
              </Button>
            </ListItem>
          ) : (
            <ListItem key={car.src}>
              <Box display="flex">
                <CarImage src={car.src} />
                <CarInfo car={car} />
              </Box>
            </ListItem>
          )
        )}
      </List>
    </Grid>
  );
};

export default YourCarsList;
