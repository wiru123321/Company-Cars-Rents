import React from "react";
import {
  Container,
  TextField,
  Grid,
  Input,
  InputLabel,
  Button,
} from "@material-ui/core";
export const UserPersonalData = () => {
  return (
    <Grid direction="row" justify="left" alignItems="center">
      <TextField label="Firstname" variant="outlined" margin="normal" />
      <TextField label="Lastname" variant="outlined" margin="normal" />
    </Grid>
  );
};

export const RequestDate = () => {
  return (
    <Grid container direction="row" justify="flex-start" alignItems="center">
      <InputLabel htmlFor="id">Request date: </InputLabel>
      <TextField
        id="id"
        type="date"
        variant="outlined"
        shrink
        margin="normal"
      />
    </Grid>
  );
};

export const ReservationDate = () => {
  return (
    <div>
      <Grid container direction="row" justify="flex-start" alignItems="center">
        <InputLabel>Reservation start: </InputLabel>
        <TextField type="date" variant="outlined" shrink margin="normal" />
        <InputLabel>hour: </InputLabel>
        <TextField type="time" variant="outlined" shrink margin="normal" />
      </Grid>
      <Grid container direction="row" justify="flex-start" alignItems="center">
        <InputLabel>Reservation end date: </InputLabel>
        <TextField type="date" variant="outlined" shrink margin="normal" />
        <InputLabel>hour: </InputLabel>
        <TextField type="time" variant="outlined" shrink margin="normal" />
      </Grid>
    </div>
  );
};
