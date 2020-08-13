import React from "react";
import ParkingSlotInfo from "./ParkingSlotInfo";
import { Typography, Grid, Divider } from "@material-ui/core";

const ParkingInfo = ({ parkingFrom, parkingTo }) => (
  <Grid container direction="row" justify="space-evenly" alignItems="center">
    <Grid item xs={4}>
      <Typography>Car will be picked from:</Typography>
      <ParkingSlotInfo parking={parkingFrom} />
    </Grid>
    <Divider orientation="vertical" flexItem />
    <Grid item xs={4}>
      <Typography>Car will be left at:</Typography>
      <ParkingSlotInfo parking={parkingTo} />
    </Grid>
  </Grid>
);

export default ParkingInfo;
