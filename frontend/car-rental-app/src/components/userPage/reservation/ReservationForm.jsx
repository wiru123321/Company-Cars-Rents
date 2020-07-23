import React from "react";
import {
  TextField,
  Grid,
  InputLabel,
  FormGroup,
  FormControlLabel,
  Box,
} from "@material-ui/core";
import useStyles from "./useStyles";

export const UserPersonalData = ({
  handleFirstnameChange,
  handleLastnameChange,
}) => {
  return (
    <Box
      border={1}
      className={useStyles().borderedBox}
      borderRadius="borderRadius"
    >
      <Grid container direction="row" justify="space-between">
        <TextField
          onChange={(event) => handleFirstnameChange(event.target.value)}
          label="Firstname"
          variant="outlined"
          margin="normal"
          required
        />
        <TextField
          onChange={(event) => handleLastnameChange(event.target.value)}
          label="Lastname"
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
    <Box
      border={1}
      className={useStyles().borderedBox}
      borderRadius="borderRadius"
    >
      <InputLabel>{inputText}</InputLabel>
      <FormGroup row>
        <FormControlLabel
          label="Date:"
          labelPlacement="start"
          control={
            <TextField
              onChange={(event) => handleDateChange(event.target.value)}
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
              onChange={(event) => handleHourChange(event.target.value)}
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
