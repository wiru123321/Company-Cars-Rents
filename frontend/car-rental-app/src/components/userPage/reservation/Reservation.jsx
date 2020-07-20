import React from "react";
import CarSuggestion from "./CarSuggestion";
import useStyles from "./useStyles";
import { Container, Grid, Button, Box } from "@material-ui/core";
import { ReservationDate, UserPersonalData } from "./ReservationForm";
import { useSelector, useDispatch } from "react-redux";
import {
  firstnameChange,
  lastnameChange,
  beginDateChange,
  beginHourChange,
  endDateChange,
  endHourChange,
} from "../../../features/car-reservation/reservationSlice";

const Reservation = () => {
  const dispatch = useDispatch();
  return (
    <Container className={useStyles().root} maxWidth="lg">
      <Grid container direction="row" justify="left" alignItems="flex-start">
        <Box className={useStyles().leftColumn}>
          <Grid direction="column" justify="flex-start" alignItems="center">
            <UserPersonalData
              handleFirstnameChange={dispatch(firstnameChange)}
              handleLastnameChange={dispatch(lastnameChange)}
            />
            <ReservationDate
              inputText="Reservation start:"
              handleDateChange={dispatch(beginDateChange)}
              handleHourChange={dispatch(beginHourChange)}
            />
            <ReservationDate
              inputText="Reservation end:"
              handleDateChange={dispatch(endDateChange)}
              handleHourChange={dispatch(endHourChange)}
            />
            <Button
              style={{ marginTop: "2%", width: "100%" }}
              variant="contained"
              color="primary"
            >
              Submit
            </Button>
          </Grid>
        </Box>
        <Container className={useStyles().rightColumn}>
          <CarSuggestion />
        </Container>
      </Grid>
    </Container>
  );
};

export default Reservation;
