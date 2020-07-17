import React from "react";
import {
  Container,
  TextField,
  Grid,
  Input,
  InputLabel,
  Button,
} from "@material-ui/core";

import {
  RequestDate,
  ReservationDate,
  UserPersonalData,
} from "./ReservationForm";

const CarSuggestion = () => {
  return (
    <Grid container direction="column" justify="flex-start" alignItems="center">
      Elo
    </Grid>
  );
};
const Reservation = () => {
  return (
    <Container>
      <Grid container direction="row" justify="flex-start" alignItems="center">
        <Container style={{ width: "50%" }}>
          <Grid direction="column" justify="flex-start" alignItems="center">
            <UserPersonalData />
            <RequestDate />
            <ReservationDate />
          </Grid>
        </Container>
        <Container style={{ width: "50%" }}>
          <CarSuggestion />
        </Container>
      </Grid>
      <Button>Submit</Button>
    </Container>
  );
};

export default Reservation;
