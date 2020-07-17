import React from "react";
import {
  Container,
  TextField,
  Grid,
  Input,
  InputLabel,
  Button,
  Box,
  ListItem,
} from "@material-ui/core";
import {
  RequestDate,
  ReservationDate,
  UserPersonalData,
} from "./ReservationForm";
import CarSuggestion from "./CarSuggestion";

const Reservation = () => {
  return (
    <Container maxWidth="lg">
      <Grid container direction="row" justify="left" alignItems="flex-start">
        <Container style={{ width: "40%" }}>
          <Grid direction="column" justify="flex-start" alignItems="center">
            <UserPersonalData />
            <RequestDate />
            <ReservationDate />
            <Button>Submit</Button>
          </Grid>
        </Container>
        <Container style={{ width: "60%" }}>
          <CarSuggestion />
        </Container>
      </Grid>
    </Container>
  );
};

export default Reservation;
