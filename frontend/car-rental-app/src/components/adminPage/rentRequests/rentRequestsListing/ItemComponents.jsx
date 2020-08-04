import React from "react";
import { Grid } from "@material-ui/core";

export const UserData = ({ firstname, lastname }) => {
  return (
    <React.Fragment>
      <Grid item xs={4}>
        <h1>
          {firstname} {lastname}
        </h1>
      </Grid>
    </React.Fragment>
  );
};

export const ReservationDate = ({ beginDate, beginHour, endDate, endHour }) => {
  return (
    <React.Fragment>
      <Grid item xs={4}>
        Reservation start: {beginDate} - {beginHour}
      </Grid>
      <Grid item xs={4}>
        Reservation end: {endDate} - {endHour}
      </Grid>
    </React.Fragment>
  );
};
