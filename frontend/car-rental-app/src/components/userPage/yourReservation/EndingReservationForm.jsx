import React from "react";
import {
  Grid,
  Container,
  List,
  ListItem,
  Box,
  Button,
} from "@material-ui/core";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import { useSelector, useDispatch } from "react-redux";
import {
  selectCars,
  selectIndex,
} from "../../../features/your-cars/yourCarsSlice";
import { ParkingData } from "./ReservationDataForm";
import {
  parkingNumberChange,
  parkingPlaceNumberChange,
  acceptForm,
} from "../../../features/your-cars/yourCarsSlice";

const EndingReservationForm = () => {
  const dispatch = useDispatch();
  const cars = useSelector(selectCars);
  const selectCarIndex = useSelector(selectIndex);
  return (
    <Container maxWidth="lg">
      <Grid container direction="row" justify="center" alignItems="flex-start">
        <List>
          <ListItem key={cars[selectCarIndex].src}>
            <Box display="flex">
              <CarImage src={cars[selectCarIndex].src} />
              <CarInfo car={cars[selectCarIndex]} />
            </Box>
          </ListItem>
        </List>

        <ParkingData
          handleParkingNumberChange={dispatch(parkingNumberChange())}
          handleParkingPlaceChange={dispatch(parkingPlaceNumberChange())}
        />
      </Grid>
      <Grid container direction="row" justify="center">
        <Button
          variant="outlined"
          color="primary"
          onClick={() => {
            dispatch(acceptForm());
          }}
        >
          Give Car Back
        </Button>
      </Grid>
    </Container>
  );
};

export default EndingReservationForm;
