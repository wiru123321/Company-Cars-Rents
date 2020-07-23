import React from "react";
import {
  TextField,
  Grid,
  InputLabel,
  FormGroup,
  FormControlLabel,
  Box,
} from "@material-ui/core";

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

export const ReservationDate = ({
  inputText,
  handleDateChange,
  handleHourChange,
}) => {
  return (
    <Box border={1} borderRadius="borderRadius">
      <InputLabel>{inputText}</InputLabel>
      <FormGroup row>
        <FormControlLabel
          label="Date:"
          labelPlacement="start"
          control={
            <TextField
              onChange={() => handleDateChange}
              type="date"
              variant="outlined"
              shrink
              margin="normal"
              required
            />
          }
        />
        <FormControlLabel
          label="Hour:"
          labelPlacement="start"
          control={
            <TextField
              onChange={() => handleHourChange}
              type="time"
              variant="outlined"
              shrink
              margin="normal"
              required
            />
          }
        />
      </FormGroup>
    </Box>
  );
};
