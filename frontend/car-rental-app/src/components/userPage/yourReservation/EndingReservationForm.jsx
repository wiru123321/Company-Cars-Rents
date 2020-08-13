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
  selectReservation,
  selectIndex,
  parkingNumberChange,
  parkingPlaceNumberChange,
  acceptForm,
} from "../../../features/your-cars/yourCarsSlice";
import { ParkingData } from "./ReservationDataForm";
import BugReport from "./BugReport";

const EndingReservationForm = () => {
  const dispatch = useDispatch();
  const reservation = useSelector(selectReservation);
  const selectCarIndex = useSelector(selectIndex);
  return (
    <Container maxWidth="lg">
      <Grid container direction="row" justify="center">
        <Grid xs={6}>
          <List>
            <ListItem key={selectCarIndex}>
              <Box display="flex">
                <CarImage
                  src={
                    "http://localhost:8080/u/car/download-car-image/" +
                    reservation[selectCarIndex].carDTO.licensePlate
                  }
                />
                <CarInfo car={reservation[selectCarIndex].carDTO} />
              </Box>
            </ListItem>
          </List>
        </Grid>
        <Grid xs={3}>
          <ParkingData
            handleParkingNumberChange={dispatch(parkingNumberChange())}
            handleParkingPlaceChange={dispatch(parkingPlaceNumberChange())}
          />
        </Grid>
        <Grid container justify="center" xs={12}>
          <BugReport />
        </Grid>
        <Grid xs={12} container justify="center" style={{ marginTop: "1vh" }}>
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
      </Grid>
    </Container>
  );
};

export default EndingReservationForm;
