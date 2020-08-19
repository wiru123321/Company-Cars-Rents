import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid, Button, Box, TextField } from "@material-ui/core";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import {
  selectCar,
  toggleChoose,
  dateIsChoosenHandler,
  isCarFormActiveHandler,
  uploadReservCar,
  selectbeginDate,
  selectbeginHour,
  selectendDate,
  selectendHour,
} from "../../../features/car-reservation/reservationSlice";
import { ParkingData } from "./ReservationDataFormReserv";

const SelectedCar = () => {
  const dispatch = useDispatch();
  const car = useSelector(selectCar);

  const [town, setTown] = useState(car.parkingDTO.town);
  const [streetName, setStreetName] = useState(car.parkingDTO.streetName);
  const [postalCode, setPostalCode] = useState(car.parkingDTO.postalCode);
  const [number, setNumber] = useState(car.parkingDTO.number);
  const [comment, setComment] = useState(car.parkingDTO.comment);
  const [commentToReservation, setCommentToReservation] = useState("");

  const beginDate = useSelector(selectbeginDate);
  const beginHour = useSelector(selectbeginHour);
  const endDate = useSelector(selectendDate);
  const endHour = useSelector(selectendHour);

  const toggleCarChoose = () => dispatch(toggleChoose());

  const toggleCarReserve = () => {
    let rentDTO = {
      dateFrom: beginDate + "T" + beginHour + ":00",
      dateTo: endDate + "T" + endHour + ":00",
      reasonForTheLoan: commentToReservation,
      adminResponseForTheRequest: "",
      faultMessage: "",
      carDTO: car,
      parkingDTOTo: {
        town: town,
        postalCode: postalCode,
        streetName: streetName,
        number: number,
        comment: comment,
      },
    };
    dispatch(uploadReservCar(car.licensePlate, rentDTO));
    dispatch(dateIsChoosenHandler());
    dispatch(isCarFormActiveHandler());
  };

  const undoSelection = () => {
    dispatch(dateIsChoosenHandler());
    dispatch(isCarFormActiveHandler());
  };
  return (
    <div>
      <Grid
        container
        justify="center"
        alignItems="center"
        style={{ marginLeft: "22%" }}
      >
        <Box display="flex">
          <CarImage
            src={
              "http://localhost:8080/u/car/download-car-image/" +
              car.licensePlate
            }
          />
          <CarInfo car={car} />
        </Box>
        <Box display="flex">
          <Button onClick={toggleCarChoose} variant="contained" color="primary">
            Change car
          </Button>
        </Box>
        <Box display="flex">
          <Button onClick={undoSelection} variant="contained" color="secondary">
            Undo selection
          </Button>
        </Box>
      </Grid>
      <Grid container justify="center" alignItems="center">
        <TextField
          onChange={(event) => setCommentToReservation(event.target.value)}
          value={commentToReservation}
          label="Why you want this car"
          variant="outlined"
          margin="normal"
          required
        />
      </Grid>
      <Grid container justify="center" alignItems="center">
        <h1 style={{ fontSize: "30px", marginTop: "5vh" }}>
          Enter parking where you want to give back the car.
        </h1>
      </Grid>

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
      <Grid container justify="center" alignItems="center">
        <Button onClick={toggleCarReserve} variant="contained" color="primary">
          Reserve car.
        </Button>
      </Grid>
    </div>
  );
};

export default SelectedCar;
