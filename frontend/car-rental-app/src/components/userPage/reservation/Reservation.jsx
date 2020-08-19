import React from "react";
import CarSuggestion from "./CarSuggestion";
import useStyles from "./useStyles";
import { Container, Grid, Button, Box } from "@material-ui/core";
import { ReservationDate } from "./ReservationForm";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchCarsAvaiableInDate,
  dateIsChoosenHandler,
  selectDateIsChoosen,
  selectIsCarFormActive,
  isCarFormActiveHandler,
  beginDateChange,
  beginHourChange,
  endDateChange,
  endHourChange,
  selectbeginDate,
  selectbeginHour,
  selectendDate,
  selectendHour,
} from "../../../features/car-reservation/reservationSlice";
import { useAlert } from "react-alert";

const Reservation = () => {
  const classes = useStyles();
  const alert = useAlert();

  const beginDate = useSelector(selectbeginDate);
  const beginHour = useSelector(selectbeginHour);
  const endDate = useSelector(selectendDate);
  const endHour = useSelector(selectendHour);

  const dispatch = useDispatch();

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
  function sumbitHander() {
    console.log(beginDate + "T" + beginHour);
    let dateFromDateTo = {
      dateFrom: beginDate + "T" + beginHour + ":00",
      dateTo: endDate + "T" + endHour + ":00",
    };

    dispatch(fetchCarsAvaiableInDate(dateFromDateTo, alert));
    if (beginDate && beginHour && endDate && endHour && endHour) {
      dispatch(dateIsChoosenHandler());
      dispatch(isCarFormActiveHandler());
    }
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
                valueDate={beginDate}
                valueHour={beginHour}
              />
              <ReservationDate
                inputText="Reservation end:"
                handleDateChange={handleEndDateChange}
                handleHourChange={handleEndHourChange}
                valueDate={endDate}
                valueHour={endHour}
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
