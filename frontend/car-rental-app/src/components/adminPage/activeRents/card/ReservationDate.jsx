import React from "react";
import { Grid, Typography } from "@material-ui/core";
import EventAvailableIcon from "@material-ui/icons/EventAvailable";
import EventBusyIcon from "@material-ui/icons/EventBusy";

const ReservationDate = ({ rent }) => {
  return (
    <Grid container justify="space-evenly">
      <Grid item>
        <Typography>Reservation start:</Typography>
        <Typography>
          <EventAvailableIcon /> {rent.dateFrom.slice(0, 10)} -{" "}
          {rent.dateFrom.slice(11, 19)}
        </Typography>
      </Grid>
      <Grid item>
        <Typography>Reservation end:</Typography>
        <Typography>
          <EventBusyIcon /> {rent.dateTo.slice(0, 10)} -{" "}
          {rent.dateTo.slice(11, 19)}
        </Typography>
      </Grid>
    </Grid>
  );
};

export default ReservationDate;
