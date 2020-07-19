import React from "react";
import CarSuggestion from "./CarSuggestion";
import useStyles from "./useStyles";
import { Container, Grid, Button, Box } from "@material-ui/core";
import { ReservationDate, UserPersonalData } from "./ReservationForm";

const Reservation = () => {
  return (
    <Container className={useStyles().root} maxWidth="lg">
      <Grid container direction="row" justify="left" alignItems="flex-start">
        <Box className={useStyles().leftColumn}>
          <Grid direction="column" justify="flex-start" alignItems="center">
            <UserPersonalData />
            <ReservationDate inputText="Reservation start:" />
            <ReservationDate inputText="Reservation end:" />
            <Button
              style={{ marginTop: "2%", width: "100%" }}
              variant="contained"
              color="primary"
            >
              Submit
            </Button>
          </Grid>
        </Box>
        <Container className={useStyles().rightColumn}>
          <CarSuggestion />
        </Container>
      </Grid>
    </Container>
  );
};

export default Reservation;
