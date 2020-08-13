import React from "react";
import { TextField, Grid, Box } from "@material-ui/core";

export const ParkingData = ({
  handleParkingNumberChange,
  handleParkingPlaceChange,
}) => {
  return (
    <Box
      display="flex"
      justifyContent="center"
      m={1}
      p={1}
      style={{ height: "10vh" }}
    >
      <Grid container direction="row" justify="space-between">
        <TextField
          onChange={() => handleParkingNumberChange}
          label="Parking Number"
          variant="outlined"
          margin="normal"
          required
        />
        <TextField
          onChange={() => handleParkingPlaceChange}
          label="Parking Place Number"
          variant="outlined"
          margin="normal"
          required
        />
      </Grid>
    </Box>
  );
};
