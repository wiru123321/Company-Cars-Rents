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
  acceptForm,
  backTheCarBack,
} from "../../../features/your-cars/yourCarsSlice";
import { ParkingData } from "./ReservationDataForm";
import BugReport from "./BugReport";
import { useAlert } from "react-alert";

const EndingReservationForm = () => {
  const dispatch = useDispatch();
  const alert = useAlert();

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
  const [bugDescribe, setBugDescribe] = useState(
    reservation[selectCarIndex].faultMessage
  );

  function submitCar() {
    let newReservation = {
      parkingDTO: {
        town: town,
        streetName: streetName,
        postalCode: postalCode,
        number: number,
        comment: comment,
      },
      faultMessage: bugDescribe,
    };
    console.log(newReservation);
    dispatch(
      backTheCarBack(reservation[selectCarIndex].id, newReservation, alert)
    );
    setTimeout(function () {
      dispatch(acceptForm());
    }, 1500);
  }
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
          <BugReport
            bugDescribe={bugDescribe}
            bugDescribeHandler={setBugDescribe}
          />
        </Grid>
        <Grid xs={12} container justify="center" style={{ marginTop: "1vh" }}>
          <Button
            variant="contained"
            color="primary"
            onClick={() => {
              submitCar();
            }}
          >
            Give the car back
          </Button>
        </Grid>
        <Grid xs={12} container justify="center" style={{ marginTop: "1vh" }}>
          <Button
            variant="outlined"
            color="primary"
            onClick={() => {
              dispatch(acceptForm());
            }}
          >
            Back to Car reservations.
          </Button>
        </Grid>
      </Grid>
    </Container>
  );
};

export default EndingReservationForm;
