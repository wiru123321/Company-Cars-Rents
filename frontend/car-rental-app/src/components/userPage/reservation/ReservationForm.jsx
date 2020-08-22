import React from "react";
import {
  TextField,
  InputLabel,
  FormGroup,
  FormControlLabel,
  Box,
} from "@material-ui/core";
import useStyles from "./useStyles";

export const ReservationDate = ({
  inputText,
  handleDateChange,
  handleHourChange,
  valueDate,
  valueHour,
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
              onChange={(event) => handleDateChange(event)}
              type="date"
              variant="outlined"
              value={valueDate}
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
              onChange={(event) => handleHourChange(event)}
              type="time"
              variant="outlined"
              value={valueHour}
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
