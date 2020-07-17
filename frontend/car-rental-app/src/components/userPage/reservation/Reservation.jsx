import React from "react";
import CarSuggestion from "./CarSuggestion";
import { makeStyles } from "@material-ui/core/styles";
import { Container, Grid, Button } from "@material-ui/core";
import {
  RequestDate,
  ReservationDate,
  UserPersonalData,
} from "./ReservationForm";

const useStyles = makeStyles((theme) => ({
  root: {
    marginTop: "2%",
    height: "800px",
    padding: "8px",
  },
  leftColumn: {
    width: "40%",
  },
  rightColumn: {
    width: "60%",
  },
}));

const Reservation = () => {
  const classes = useStyles();

  return (
    <Container className={classes.root} maxWidth="lg">
      <Grid container direction="row" justify="left" alignItems="flex-start">
        <Container className={classes.leftColumn}>
          <Grid direction="column" justify="flex-start" alignItems="center">
            <UserPersonalData />
            <RequestDate />
            <ReservationDate />
            <Button variant="contained" color="primary">
              Submit
            </Button>
          </Grid>
        </Container>
        <Container className={classes.rightColumn}>
          <CarSuggestion />
        </Container>
      </Grid>
    </Container>
  );
};

export default Reservation;
