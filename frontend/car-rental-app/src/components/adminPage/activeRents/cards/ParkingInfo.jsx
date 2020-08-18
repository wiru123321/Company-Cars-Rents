import React from "react";
import { Grid, makeStyles, Divider, Typography } from "@material-ui/core";

const useStyles = makeStyles({
  title: {
    fontSize: "1.5rem",
    textAlign: "center",
  },
});

const ParkingInfo = ({ title, parking }) => {
  const classes = useStyles();
  return (
    <Grid container direction="column">
      <Typography className={classes.title}>{title}</Typography>
      <Divider />
      <Grid container justify="space-evenly">
        <Grid>
          <Typography>Town: {parking.town}</Typography>
          <Typography>Postal code: {parking.postalCode}</Typography>
        </Grid>
        <Grid>
          <Typography>Street name: {parking.streetName}</Typography>
          <Typography>Number: {parking.number}</Typography>
        </Grid>
      </Grid>
      <Divider />
      <Grid container justify="center">
        <Typography>{parking.comment}</Typography>
      </Grid>
    </Grid>
  );
};

export default ParkingInfo;
