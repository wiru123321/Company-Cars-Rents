import React, { useState } from "react";
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
  backTheCarBack,
} from "../../../features/your-cars/yourCarsSlice";
import { ParkingData } from "./ReservationDataForm";
import BugReport from "./BugReport";

const EndingReservationForm = () => {
  const dispatch = useDispatch();
  const reservation = useSelector(selectReservation);
  const selectCarIndex = useSelector(selectIndex);
  const [town, setTown] = useState(reservation[selectCarIndex].parkingTo.town);
  const [streetName, setStreetName] = useState(
    reservation[selectCarIndex].parkingTo.streetName
  );
  const [postalCode, setPostalCode] = useState(
    reservation[selectCarIndex].parkingTo.postalCode
  );
  const [number, setNumber] = useState(
    reservation[selectCarIndex].parkingTo.number
  );
  const [comment, setComment] = useState(
    reservation[selectCarIndex].parkingTo.comment
  );
  return (
    <Container
      maxWidth="lg"
      style={{
        minHeight: "80vh",
        height: "auto",
        height: "100%",
      }}
    >
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
            handletownChange={setTown}
            handlestreetNameChange={setStreetName}
            handlepostalCodeChange={setPostalCode}
            handlenumberChange={setNumber}
            handlecommentChange={setComment}
            town={town}
            streetName={streetName}
            postalCode={postalCode}
            number={number}
            comment={comment}
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
              let newReservation = {
                town: town,
                streetName: streetName,
                postalCode: postalCode,
                number: number,
                comment: comment,
              };
              dispatch(
                backTheCarBack(reservation[selectCarIndex].id, newReservation)
              );
              console.log({ newReservation });
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
