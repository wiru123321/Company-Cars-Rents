import React, { useEffect } from "react";
import CarSuggestion from "./CarSuggestion";
import useStyles from "./useStyles";
import { Container, Grid, Button, Box } from "@material-ui/core";
import { ReservationDate, UserPersonalData } from "./ReservationForm";
import { useDispatch } from "react-redux";
import {
  firstnameChange,
  lastnameChange,
  beginDateChange,
  beginHourChange,
  endDateChange,
  endHourChange,
  fetchActiveCars,
} from "../../../features/car-reservation/reservationSlice";

const Reservation = () => {
  const classes = useStyles();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchActiveCars());
  }, []);

  function handleFirstnameChange(event) {
    dispatch(firstnameChange(event.target.value));
  }

  function handleLastnameChange(event) {
    dispatch(lastnameChange(event.target.value));
  }

  function handleBeginDateChange(event) {
    dispatch(beginDateChange(event.target.value));
  }

  function handleBeginHourChange(event) {
    dispatch(beginHourChange(event.target.value));
  }

  function handleEndDateChange(event) {
    dispatch(endDateChange(event.target.value));
  }

  function handleEndHourChange(event) {
    dispatch(endHourChange(event.target.value));
  }

  return (
    <Container
      className={classes.root}
      maxWidth="lg"
      style={{
        minHeight: "80vh",
        height: "auto",
        height: "100%",
      }}
    >
      <Grid container direction="row" justify="left" alignItems="flex-start">
        <Box className={classes.leftColumn}>
          <Grid direction="column" justify="flex-start" alignItems="center">
            <UserPersonalData
              handleFirstnameChange={handleFirstnameChange}
              handleLastnameChange={handleLastnameChange}
            />
            <ReservationDate
              inputText="Reservation start:"
              handleDateChange={handleBeginDateChange}
              handleHourChange={handleBeginHourChange}
            />
            <ReservationDate
              inputText="Reservation end:"
              handleDateChange={handleEndDateChange}
              handleHourChange={handleEndHourChange}
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
        <Container className={classes.rightColumn}>
          <CarSuggestion />
        </Container>
      </Grid>
    </Container>
  );
};

export default Reservation;
