import React, { useEffect } from "react";
import CarSuggestion from "./CarSuggestion";
import useStyles from "./useStyles";
import { Container, Grid, Button, Box } from "@material-ui/core";
import { ReservationDate, UserPersonalData } from "./ReservationForm";
import { useDispatch, useSelector } from "react-redux";
import {
  firstnameChange,
  lastnameChange,
  beginDateChange,
  beginHourChange,
  endDateChange,
  endHourChange,
  fetchActiveCars,
  dateIsChoosenHandler,
  selectDateIsChoosen,
  selectIsCarFormActive,
  isCarFormActiveHandler,
} from "../../../features/car-reservation/reservationSlice";
import { useState } from "react";

const Reservation = () => {
  const classes = useStyles();
  const [beginDate, setBeginDate] = useState("");
  const [beginHour, setBeginHour] = useState("");
  const [endDate, setEndDate] = useState("");
  const [endHour, setEndHour] = useState("");
  const dispatch = useDispatch();

  function handleBeginDateChange(event) {
    setBeginDate(event.target.value);
  }

  function handleBeginHourChange(event) {
    setBeginHour(event.target.value);
  }

  function handleEndDateChange(event) {
    setEndDate(event.target.value);
  }

  function handleEndHourChange(event) {
    setEndHour(event.target.value);
  }
  function sumbitHander() {
    // if (beginDate && beginHour && endDate && endHour) {
    // dateFrom: "2020-12-01T00:00:00",
    //   dateTo: "2020-12-06T00:00:00",
    let dateFromDateTo = {
      // dateFrom: beginDate + "T" + beginHour + ":00",
      // dateTo: endDate + "T" + endHour + ":00",
      dateFrom: "2020-12-01T00:00:00",
      dateTo: "2020-12-06T00:00:00",
    };
    dispatch(fetchActiveCars(dateFromDateTo));
    console.log(dateFromDateTo);
    dispatch(dateIsChoosenHandler());
    dispatch(isCarFormActiveHandler());
    // }
  }
  let dateIsChoosen = useSelector(selectDateIsChoosen);
  let isCarFormActive = useSelector(selectIsCarFormActive);
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
      <Grid container direction="row" justify="center">
        {dateIsChoosen ? (
          <Box>
            <Grid direction="column" alignItems="center">
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
                onClick={sumbitHander}
              >
                Submit
              </Button>
            </Grid>
          </Box>
        ) : null}
        {isCarFormActive ? (
          <Container>
            <CarSuggestion />
          </Container>
        ) : null}
      </Grid>
    </Container>
  );
};

export default Reservation;
