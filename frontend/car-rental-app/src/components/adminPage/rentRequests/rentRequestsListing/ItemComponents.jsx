import React from "react";
import { Grid, Typography, makeStyles } from "@material-ui/core";

const useStyles = makeStyles({
  title: {
    fontSize: "2rem",
  },
  contentText: {
    fontSize: "1.5rem",
  },
});

export const UserData = ({ firstname, lastname }) => {
  const classes = useStyles();

  return (
    <Grid container>
      <Grid item xs={4}>
        <Typography className={classes.title}>
          {firstname} {lastname}
        </Typography>
      </Grid>
    </Grid>
  );
};

export const ReservationDate = ({ beginDate, beginHour, endDate, endHour }) => {
  const classes = useStyles();

  return (
    <Grid container justify="space-between" alignItems="center">
      <Grid item xs={6}>
        <Typography className={classes.contentText}>
          Reservation start: {beginDate} - {beginHour}
        </Typography>
      </Grid>
      <Grid item xs={6}>
        <Typography className={classes.contentText}>
          Reservation end: {endDate} - {endHour}
        </Typography>
      </Grid>
    </Grid>
  );
};
