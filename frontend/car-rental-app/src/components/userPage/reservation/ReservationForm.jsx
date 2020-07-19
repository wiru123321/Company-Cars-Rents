import React from "react";
import {
  Container,
  TextField,
  Grid,
  InputLabel,
  FormGroup,
  FormControlLabel,
  Box,
} from "@material-ui/core";

export const UserPersonalData = () => {
  return (
    <Box border={1} style={{ padding: "12px" }} borderRadius="borderRadius">
      <Grid container direction="row" justify="space-between">
        <TextField
          label="Firstname"
          variant="outlined"
          margin="normal"
          required
        />
        <TextField
          label="Lastname"
          variant="outlined"
          margin="normal"
          required
        />
      </Grid>
    </Box>
  );
};

export const RequestDate = () => {
  return (
    <Grid
      container
      direction="column"
      justify="space-between"
      alignItems="flex-start"
    >
      <InputLabel>Request date:</InputLabel>
      <Container>
        <TextField type="date" variant="outlined" shrink margin="normal" />
      </Container>
    </Grid>
  );
};

export const ReservationDate = () => {
  return (
    <Box>
      <Box
        border={1}
        style={{ padding: "12px", marginTop: "2%" }}
        borderRadius="borderRadius"
      >
        <InputLabel>Reservation start</InputLabel>
        <FormGroup row>
          <FormControlLabel
            label="Date:"
            labelPlacement="start"
            control={
              <TextField
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
      <Box
        border={1}
        style={{ marginTop: "2%", padding: "12px" }}
        borderRadius="borderRadius"
      >
        <InputLabel>Reservation end: </InputLabel>
        <FormGroup row>
          <FormControlLabel
            label="Date:"
            labelPlacement="start"
            control={
              <TextField
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
    </Box>
  );
};
